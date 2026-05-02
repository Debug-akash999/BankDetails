package com.example.BankDetails.Repository;

import com.example.BankDetails.Model.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<user , Long> {

    Optional<user> findByEmail(String Email);
}
