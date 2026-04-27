package com.example.BankDetails.Service;


import com.example.BankDetails.Model.BankDetails;
import com.example.BankDetails.Repository.BankDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankDetailsRepo repo;
    public BankDetails createAmount(BankDetails acc) {
        if (acc.getBalance() == null) {
            acc.setBalance(BigDecimal.ZERO);
        }
        return repo.save(acc);
    }
    @Transactional
    public BankDetails deposit(Long id, BigDecimal amount) {
        if (id == null || amount == null) {
            throw new RuntimeException("Invalid request");
        }
        BankDetails acc = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        acc.setBalance(acc.getBalance().add(amount));
        return acc;
    }

    @Transactional
    public BankDetails withdraw(Long id, BigDecimal amount) {
        if (id == null || amount == null) {
            throw new RuntimeException("Invalid request");
        }
        BankDetails acc = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (acc.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        acc.setBalance(acc.getBalance().subtract(amount));
        return acc;
    }
    @Transactional
    public String transfer(Long fromId, Long toId, BigDecimal amount) {
        if (fromId == null || toId == null || amount == null) {
            throw new RuntimeException("Invalid transfer request");
        }
        BankDetails from = repo.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        BankDetails to = repo.findById(toId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));
        if (from.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));
        return "Transfer successful";
    }

    public List<BankDetails> getAll(){
        return repo.findAll()
;    }
}