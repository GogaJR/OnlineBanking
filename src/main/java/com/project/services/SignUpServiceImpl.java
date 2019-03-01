package com.project.services;

import com.project.forms.BankForm;
import com.project.forms.UserForm;
import com.project.models.Bank;
import com.project.models.Role;
import com.project.models.State;
import com.project.models.User;
import com.project.repositories.BankRepository;
import com.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    @Override
    public boolean isMalePresent(String mail) {
        Optional<User> user = userRepository.findOneByMail(mail);
        Optional<Bank> bank = bankRepository.findOneByMail(mail);
        if(user.isPresent() || bank.isPresent()) {
            return true;
        }

        return false;
    }

    @Override
    public void signUp(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());

        User newUser = User.builder()
                .name(userForm.getName())
                .surname(userForm.getSurname())
                .sex(userForm.getSex())
                .dateOfBirth(Date.valueOf(userForm.getYearOfBirth() + "-" + userForm.getMonthOfBirth() + "-" + userForm.getDayOfBirth()))
                .placeOfBirth(userForm.getPlaceOfBirth())
                .placeOfLiving(userForm.getPlaceOfLiving())
                .role(Role.ROLE_USER)
                .state(State.ACTIVE)
                .passportSerialNumber(userForm.getPassportSerialNumber())
                .mail(userForm.getMail())
                .hashPassword(hashPassword)
                .build();

        userRepository.save(newUser);
    }

    @Override
    public void signUp(BankForm bankForm) {
        String hashPassword = passwordEncoder.encode(bankForm.getPassword());
        Bank newBank = Bank.builder()
                .name(bankForm.getName())
                .mail(bankForm.getMail())
                .workingHours(bankForm.getWorkingHours())
                .phoneNumber(bankForm.getPhoneNumber())
                .hashPassword(hashPassword)
                .fax(bankForm.getFax())
                .role(Role.ROLE_BANK)
                .state(State.ACTIVE)
                .daysOff(bankForm.getDaysOff())
                .account(bankForm.getAccount())
                .address(bankForm.getAddress())
                .build();

        bankRepository.save(newBank);
    }
}
