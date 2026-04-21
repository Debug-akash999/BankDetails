package com.example.BankDetails.Service;


import com.example.BankDetails.Model.BankDetails;
import com.example.BankDetails.Repository.BankDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    @Autowired
   private BankDetailsRepo repo;

    public BankDetails createAmount(BankDetails cc){
        return repo.save(cc);
    }
    public BankDetails deposit(Long id, double balance) {
       BankDetails acc=repo.findById(id).orElseThrow();
       acc.setBalance(acc.getBalance()+ balance);
       return  repo.save(acc);
    }
    public BankDetails withdraw(Long id,double balance){
        BankDetails accc=repo.findById(id).orElseThrow();
        if(accc.getBalance()<balance){
            throw  new RuntimeException("insufficient balance");

        }
        accc.setBalance(accc.getBalance()-balance);
        return repo.save(accc);
    }
    public String transfer(Long formId,Long toId,double balance){

        BankDetails from= repo.findById(formId).orElseThrow();
        BankDetails to= repo.findById(toId).orElseThrow();
        if(from.getBalance()<balance){
            throw  new RuntimeException("low balance");
        }
        from.setBalance(from.getBalance()-balance);
        to.setBalance(to.getBalance()+balance);
        repo.save(from);
        repo.save(to);
        return " Transfer successful";
    }
}
