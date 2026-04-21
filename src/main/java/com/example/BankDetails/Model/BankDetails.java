package com.example.BankDetails.Model;


import jakarta.persistence.*;

@Entity
@Table(name="BankDetails")

public class BankDetails {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String  name;
    private double balance;

   public  BankDetails(){};

   public void BnakDetails(Long id, String name,double balance){

       this.id=id;
       this.name=name;
       this.balance=balance;
   }

   public void setId(Long id){
       this.id=id;
   }
   public Long getId(){
       return id;
   }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
