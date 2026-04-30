package com.example.BankDetails.Service;

import com.example.BankDetails.Model.BankDetails;
import com.example.BankDetails.Model.Transaction;
import com.example.BankDetails.Repository.BankDetailsRepo;
import com.example.BankDetails.Repository.TransactionRepo;   // ← added
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankDetailsRepo repo;

    @Autowired
    private TransactionRepo transactionRepo;

    public BankDetails createAmount(BankDetails acc) {
        if (acc.getBalance() == null) {
            acc.setBalance(BigDecimal.ZERO);
        }
        return repo.save(acc);
    }
    @Transactional
    public BankDetails deposit(Long id, BigDecimal amount) {
        if (id == null || amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Invalid request");
        }
        BankDetails acc = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        acc.setBalance(acc.getBalance().add(amount));
        return repo.save(acc);
    }
    @Transactional
    public BankDetails withdraw(Long id, BigDecimal amount) {
        if (id == null || amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Invalid request");
        }
        BankDetails acc = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (acc.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        acc.setBalance(acc.getBalance().subtract(amount));
        return repo.save(acc);
    }
    @Transactional
    public Transaction transfer(Long fromId, Long toId, BigDecimal amount) {
        Transaction txn = new Transaction();
        txn.setFromAccount(fromId);
        txn.setToAccount(toId);
        txn.setAmount(amount);
        txn.setStatus("PENDING");
        BankDetails from = repo.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        BankDetails to = repo.findById(toId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));
        if (from.getBalance().compareTo(amount) < 0) {
            txn.setStatus("FAILED");
            return transactionRepo.save(txn);
        }
        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));
        repo.save(from);
        repo.save(to);
        txn.setStatus("SUCCESS");
        return transactionRepo.save(txn);
    }
    public List<BankDetails> getAll() {
        return repo.findAll();
    }
}