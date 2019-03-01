package com.project.details;

import com.project.models.Bank;
import com.project.models.User;
import com.project.repositories.BankRepository;
import com.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Optional<User> userCandidate = userRepository.findOneByMail(mail);
        Optional<Bank> bankCandidate = bankRepository.findOneByMail(mail);
        if(userCandidate.isPresent()) {
            return new UserDetailsImpl(userCandidate.get());
        } else if(bankCandidate.isPresent()) {
            return new UserDetailsImpl(bankCandidate.get());
        } else {
            throw new IllegalArgumentException("User Not Found");
        }
    }
}
