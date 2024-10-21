package com.estsoft.springprojectblogexam.user.service;

import com.estsoft.springprojectblogexam.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    UserRepository repository;

    UserDetailService(UserRepository repository) {
        this.repository=repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {// username -> 사용자가 입력한 email
        return repository.findByEmail(username)
                .orElseThrow(()->new IllegalArgumentException("없는 email 입니다. : " + username));
    }
}
