package com.project.services;

import com.project.details.UserDetailsImpl;
import com.project.joins.UserBankId;
import com.project.models.*;
import com.project.repositories.*;
import com.project.transfer.BankDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountResponseService accountResponseService;

    @Autowired
    private AccountRequestService accountRequestService;

    @Autowired
    private UserBalanceBankService userBalanceBankService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private PutInDepositService putInDepositService;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private DepositMonthRateRepository depositMonthRateRepository;

    @Override
    public Long getBankId(Authentication authentication) {
        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
        Long bankId = details.getBank().getId();

        return bankId;
    }

    @Override
    public Bank getBankById(Long bankId) {
        return bankRepository.findOne(bankId);
    }

    @Override
    public Optional<Bank> getBankByAccount(String account) {
        return bankRepository.findByAccount(account);
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public List<Bank> getAllBanksByUserId(Long userId) {
        List<UserBalanceBank> userBalanceBanks = userBalanceBankService.getUserBalanceBanksByUserId(userId);
        List<Bank> userBankList = new ArrayList<>();
        for (UserBalanceBank userBalanceBank : userBalanceBanks) {
            Bank bank = userBalanceBank.getBank();
            userBankList.add(bank);
        }

        return userBankList;
    }

    @Override
    public List<BankDto> getBankDtosByUserId(Long userId) {
        List<Bank> userBanks = getAllBanksByUserId(userId);
        List<BankDto> userBankDtos = new ArrayList<>();
        for(Bank bank : userBanks) {
            BankDto bankDto = BankDto.from(bank);
            userBankDtos.add(bankDto);
        }

        return userBankDtos;
    }

    @Override
    public void sendResponse(Long userId, Long bankId, String cardId, Response response, long userBalance) {
        User user = userService.getUserById(userId);
        Bank bank = bankRepository.findOne(bankId);
        AccountResponse accountResponse = AccountResponse.builder()
                                    .id(new UserBankId(userId, bankId))
                                    .user(user)
                                    .bank(bank)
                                    .response(response)
                                    .recordState(RecordState.HAS_NOT_BEEN_READ)
                                    .cardId(cardId)
                                    .build();
        accountResponseService.saveAccountResponse(accountResponse);

        AccountRequest request = accountRequestService.getAccountRequestByUserBankId(new UserBankId(userId, bankId));
        request.setResponse(response);
        accountRequestService.saveAccountRequest(request);

        if(response.equals(Response.ACCEPTED)) {
            UserBalanceBank userBalanceBank = UserBalanceBank.builder()
                    .id(new UserBankId(userId, bankId))
                    .user(user)
                    .bank(bank)
                    .cardId(cardId)
                    .balance(userBalance)
                    .build();
            userBalanceBankService.saveUserBalanceBank(userBalanceBank);
        }
    }

    @Override
    public void addDeposit(String depositName, int minAmount, Long bankId, List<Integer> monthLowerBounds,
                            List<Integer> monthUpperBounds, List<Double> interestRates) {
        Bank bank = bankRepository.findOne(bankId);
        Deposit deposit = Deposit.builder()
                .name(depositName)
                .minAmount(minAmount)
                .bank(bank)
                .build();
        depositService.saveDeposit(deposit);

        Optional<Deposit> depositCandidate = depositService.getDepositByNameAndBankId(depositName, bankId);
        if(depositCandidate.isPresent()) {
            Long depositId = depositCandidate.get().getId();
            addDepositMonthRangeRate(monthLowerBounds, monthUpperBounds, interestRates, depositId);
        }
    }

    @Override
    public void saveDepositRecordState(PutInDeposit putInDeposit) {
        putInDepositService.savePutInDeposit(putInDeposit);
    }

    @Override
    public void increaseDeposit(PutInDeposit putInDeposit) {
        double interestRate = putInDeposit.getInterestRate();
        int amount = putInDeposit.getAmount();
        int newAmount = (int) (amount + (amount*interestRate)/100);

        int newMonthsLeft = putInDeposit.getMonthsLeft() - 1;
        if(newMonthsLeft == 0) {
            Long userId = putInDeposit.getUser().getId();
            Long bankId = putInDeposit.getBank().getId();
            Optional<UserBalanceBank> userBalanceBankCandidate = userBalanceBankService.getUserBalanceBankByUserIdAndBankId(userId, bankId);
            if(userBalanceBankCandidate.isPresent()) {
                UserBalanceBank userBalanceBank = userBalanceBankCandidate.get();
                long balance = userBalanceBank.getBalance();
                long userNewBalance = balance + newAmount;
                userBalanceBank.setBalance(userNewBalance);
                userBalanceBankService.saveUserBalanceBank(userBalanceBank);
            }

            putInDeposit.setDepositState(DepositState.FINISHED);
            putInDepositService.savePutInDeposit(putInDeposit);
            return;
        }
        Date nextIncreasingDay = Date.valueOf(putInDeposit.getIncreasingDay().toLocalDate().plusDays(1));

        putInDeposit.setAmount(newAmount);
        putInDeposit.setIncreasingDay(nextIncreasingDay);
        putInDeposit.setMonthsLeft(newMonthsLeft);
        putInDepositService.savePutInDeposit(putInDeposit);
    }

    public void addDepositMonthRangeRate(List<Integer> monthLowerBounds, List<Integer> monthUpperBounds,
                                            List<Double> interestRates, Long depositId) {
        Deposit deposit = depositService.getDepositById(depositId);
        for(int index=0; index<monthLowerBounds.size(); index++) {
            DepositMonthRate depositMonthRate = DepositMonthRate.builder()
                                                    .monthLowerBound(monthLowerBounds.get(index))
                                                    .monthUpperBound(monthUpperBounds.get(index))
                                                    .interestRate(interestRates.get(index))
                                                    .deposit(deposit)
                                                    .build();

            depositMonthRateRepository.save(depositMonthRate);
        }
    }
}
