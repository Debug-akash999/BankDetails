package com.example.BankDetails.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromAccount;
    private Long toAccount;
    private BigDecimal amount;
    private String status; // PENDING, SUCCESS, FAILED
    private String type;   // UPI, CARD, NETBANKING
}