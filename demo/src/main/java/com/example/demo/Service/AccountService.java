package com.example.demo.Service;

import org.springframework.stereotype.Service;
import com.example.demo.Model.Account;
import com.example.demo.Repository.AccountRepository;
import java.util.Optional;

@Service 
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> getById(Long id){
        Optional<Account> account = accountRepository.findById(id);
        return account;
    }

    public Account createAccount(String accountNumber, String ownerName, double balance){
        Account acc = new Account(accountNumber, ownerName, balance);
        return accountRepository.save(acc);
    }


}
