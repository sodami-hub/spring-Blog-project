package com.estsoft.springprojectblogexam.user.service;

import com.estsoft.springprojectblogexam.user.entity.Users;
import com.estsoft.springprojectblogexam.user.entity.dto.AddUserRequest;
import com.estsoft.springprojectblogexam.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    //회원가입 처리(비지니스 로직)
    // password는 무조건 인코딩/디코딩

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    UserService(UserRepository repo, BCryptPasswordEncoder passwordEncoder) {
        this.repository =repo;
        this.encoder=passwordEncoder;
    }

    public Users save(AddUserRequest dto) {
        return repository.save(
                new Users(
                        dto.getEmail(),
                        encoder.encode(dto.getPassword())
                )
        );
    }
}
