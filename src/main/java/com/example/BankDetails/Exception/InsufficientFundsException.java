package com.example.BankDetails.Exception;

public class InsufficientFundsException extends RuntimeException{
    public  InsufficientFundsException(Long id){
        super("Insufficients balanace amount :"+id);
    }
}
