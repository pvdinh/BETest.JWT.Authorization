package com.example.BETest.service;

import com.example.BETest.object.AccountCredentials;
import com.example.BETest.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AccountCredentials accountCredentials = accountRepository.findAccountCredentialsByUsername(s);
        if (accountCredentials == null) {
            throw new NullPointerException("NOT FOUND : " + s);
        } else {
            return org.springframework.security.core.userdetails.User
                    .withUsername(accountCredentials.getUsername())
                    .password("{noop}"+accountCredentials.getPassword())
                    .authorities(Collections.emptyList())
                    .build();
        }
    }

    public AccountCredentials findAccountCredentialByUsername(String username) {
        return accountRepository.findAccountCredentialsByUsername(username);
    }
}
