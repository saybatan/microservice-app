package com.saybatan.accountservice.service;

import com.saybatan.accountservice.entity.Account;
import com.saybatan.accountservice.repository.AccountRepository;
import com.saybatan.servicecommon.client.contract.AccountDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountDto get(String id) {
        Account account = accountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(account, AccountDto.class);
    }

    public List<AccountDto> getAll() {
        List<Account> accountList = accountRepository.findAll();
        List<AccountDto> accountDtoList = new ArrayList<>();
        for (Account account: accountList) {
            accountDtoList.add(modelMapper.map(account, AccountDto.class));
        }
        return accountDtoList;
    }

    public List<AccountDto> getAllPageable(Pageable pageable) {
        Slice<Account> all = accountRepository.findAll(pageable);
        List<AccountDto> accountDtoList = new ArrayList<>();
//        for (Account account: accountList) {
//            accountDtoList.add(modelMapper.map(account, AccountDto.class));
//        }
        return accountDtoList;
    }

    public AccountDto save(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        account = accountRepository.save(account);
        return modelMapper.map(account, AccountDto.class);
    }

    public AccountDto update(String id, AccountDto accountDto) {
        Assert.isNull(id, "Id cannot be null");
        Optional<Account> account = accountRepository.findById(id);
        Account updatedAccount = account.map(it -> {
            it.setName(accountDto.getName());
            it.setSurname(accountDto.getSurname());
            it.setUsername(accountDto.getUsername());
            it.setEmail(accountDto.getEmail());
            return it;
        }).orElseThrow(IllegalArgumentException::new);
        updatedAccount = accountRepository.save(updatedAccount);

        return modelMapper.map(updatedAccount, AccountDto.class);
    }

    public void delete(String id) {
        Account account = accountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        accountRepository.delete(account);
    }
}
