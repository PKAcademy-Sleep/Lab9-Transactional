package com.example.demo.Controller;

import com.example.demo.Model.Account;
import com.example.demo.Service.AccountService;
import com.example.demo.Service.DepositService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final DepositService depositService;


    public AccountController(AccountService accountService, DepositService depositService) {
        this.accountService = accountService;
        this.depositService = depositService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account request) {
        Account created = accountService.createAccount(
                request.getAccountNumber(),
                request.getOwnerName(),
                request.getBalance()
        );
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        Optional<Account> account = accountService.getById(id);
        return account
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long id, @RequestBody DepositRequest request) {
        depositService.deposit(id, request.getAmount());

        Optional<Account> updated = accountService.getById(id);
        return updated
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    public static class DepositRequest {
        private Double amount;

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
    }
}