package com.example.BankDetails.Controller;

import com.example.BankDetails.DTO.TransferDTO;
import com.example.BankDetails.Model.BankDetails;
import com.example.BankDetails.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bankDetails")
public class BankDetailsController {

    @Autowired
    private BankService serivce;

    @PostMapping("createAmount")
    public BankDetails createAmount(@RequestBody BankDetails ccc){
        return serivce.createAmount(ccc);
    }

    @PostMapping("deposit/{id}")

    public BankDetails deposit(@PathVariable Long id, @RequestParam double amount){
        return serivce.deposit(id,amount);
    }

    @PostMapping("withdraw")
    public BankDetails withdraw(@PathVariable Long id,@RequestParam double amount){
        return serivce.withdraw(id,amount);
    }

    @PostMapping("Transfer")
    public String transfer(@RequestBody TransferDTO dto){
        return serivce.transfer(dto.fromId,dto.toId,dto.amount);
    }
}
