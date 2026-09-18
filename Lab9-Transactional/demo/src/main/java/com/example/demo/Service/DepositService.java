package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Model.Account;
import com.example.demo.Model.DepositTransaction;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.DepositRepository;
import jakarta.transaction.Transactional;

@Service 
public class DepositService {
    private final DepositRepository depositRepository;
    private final AccountRepository accountRepository;

    
    public DepositService(DepositRepository depositRepository, AccountRepository accountRepository) {
        this.depositRepository = depositRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional 
    public void deposit(Long accountId, Double amount){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found: "+accountId));

        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);

        DepositTransaction depositTransaction = new DepositTransaction();
        depositTransaction.setAmount(amount);
        depositTransaction.setAccount_id(account);
        depositRepository.save(depositTransaction);
    }



    


}
