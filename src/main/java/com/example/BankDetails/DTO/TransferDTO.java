package com.example.BankDetails.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferDTO {

    private Long fromId;
    private Long toId;
    private BigDecimal amount;
}