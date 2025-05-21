package com.saybatan.accountservice.service;

import com.saybatan.accountservice.entity.Account;
import com.saybatan.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account get(String id) {
        return accountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Account update(String id, Account account) {
        Assert.isNull(id, "Id cannot be null");
        return accountRepository.save(account);
    }

    public void delete(String id) {
        System.out.println("deleted");
    }
}
