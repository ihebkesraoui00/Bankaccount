package org.sid.bank_account_service.web;

import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.mapper.AccountMapper;
import org.sid.bank_account_service.repositories.BankAcoountRepository;
import org.sid.bank_account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController
{
    @Autowired
    private BankAcoountRepository bankAcoountRepository;
    @Autowired
    private AccountService accountService ;
    @Autowired
    private AccountMapper   accountMapper;
    public AccountRestController(BankAcoountRepository bankAcoountRepository) {
        this.bankAcoountRepository = bankAcoountRepository;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAcoountRepository.findAll();
}
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAcoountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found", id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccount save(@RequestBody BankAccount bankAccount){
        return bankAcoountRepository.save(bankAccount);
    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount){
        BankAccount account= bankAcoountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance() != null)account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreateAt() != null)account.setCreateAt(new Date());
        if (bankAccount.getType() != null)account.setType(bankAccount.getType());
            if (bankAccount.getCurrency() != null) account.setCurrency(bankAccount.getCurrency());
        return bankAcoountRepository.save(account);
    }
    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
                  bankAcoountRepository.deleteById(id);}
}
