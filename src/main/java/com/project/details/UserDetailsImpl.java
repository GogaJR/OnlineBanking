package com.project.details;

import com.project.models.Bank;
import com.project.models.State;
import com.project.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {
    private User user;
    private Bank bank;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public UserDetailsImpl(Bank bank) {
        this.bank = bank;
    }

    public User getUser() {
        return user;
    }

    public Bank getBank() {
        return bank;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(user != null) {
            String userRole = user.getRole().name();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole);
            return Collections.singletonList(authority);
        }

        String bankRole = bank.getRole().name();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(bankRole);
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        if(user != null) {
            return user.getHashPassword();
        }

        return bank.getHashPassword();
    }

    @Override
    public String getUsername() {
        if(user != null) {
            return user.getMail();
        }

        return bank.getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(user != null) {
            return !user.getState().equals(State.BANNED);
        }

        return !bank.getState().equals(State.DELETED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(user != null) {
            return user.getState().equals(State.ACTIVE);
        }

        return bank.getState().equals(State.ACTIVE);
    }
}
