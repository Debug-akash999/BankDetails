package com.example.BankDetails.Controller;

import com.example.BankDetails.DTO.AmountDTO;
import com.example.BankDetails.DTO.TransferDTO;
import com.example.BankDetails.Model.BankDetails;
import com.example.BankDetails.Service.BankService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class BankDetailsController {

    @Autowired
    private BankService service;

    @PostMapping("/createAccount")
    public BankDetails createAccount(@RequestBody BankDetails account) {
        return service.createAmount(account);
    }
    @PostMapping("/{id}/deposit")
    public BankDetails deposit(@PathVariable Long id,
                               @RequestBody AmountDTO dto) {
        return service.deposit(id, dto.getAmount());
    }
    @PostMapping("/{id}/withdraw")
    public BankDetails withdraw(@PathVariable Long id,
                                @RequestBody AmountDTO dto) {
        return service.withdraw(id, dto.getAmount());
    }
    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferDTO dto) {
        return service.transfer(
                dto.getFromId(),
                dto.getToId(),
                dto.getAmount()
        );
    }

    @GetMapping("getAll")

    public List<BankDetails> getAll(){
        return service.getAll();
    }
}