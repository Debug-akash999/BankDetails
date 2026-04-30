package com.example.BankDetails.Exception;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(Long id){
        super("Account not found with id:"+id);
    }
}
