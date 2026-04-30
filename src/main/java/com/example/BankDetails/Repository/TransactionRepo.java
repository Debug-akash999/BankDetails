package com.example.BankDetails.Repository;

import com.example.BankDetails.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction ,Long> {

    List<Transaction> findByFromAccountOrToAccount(Long fromAccount,Long toAccount)
;
}
