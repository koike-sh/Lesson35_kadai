package com.techacademy.service;


import java.util.List;

import org.springframework.stereotype.Service;
import com.techacademy.entity.Authentication;
import com.techacademy.repository.AuthenticationRepository;

@Service
public class AuthenticationService {
    private final AuthenticationRepository AuthenticationRepository;

    public AuthenticationService(AuthenticationRepository repository) {
        this.AuthenticationRepository = repository;
    }

    /** 全件を検索して返す */
    public List<Authentication> getAuthenticationList() {
        // リポジトリのfindAllメソッドを呼び出す
        return AuthenticationRepository.findAll();
    }
}
