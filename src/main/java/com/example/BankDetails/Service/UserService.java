package com.example.BankDetails.Service;

import com.example.BankDetails.Model.user;
import com.example.BankDetails.Repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }
    public user createUser(user user) {
        return repo.save(user);
    }
    public user getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}