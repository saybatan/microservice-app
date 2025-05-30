package com.saybatan.accountservice.service;

import com.saybatan.accountservice.dto.AccountSaveRequestDto;
import com.saybatan.accountservice.entity.Account;
import com.saybatan.accountservice.repository.AccountRepository;
import com.saybatan.servicecommon.client.contract.AccountResponseDto;
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

    public AccountResponseDto getById(String id) {
        Account account = accountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(account, AccountResponseDto.class);
    }

    public AccountResponseDto getByUsername(String username) {
        Optional<Account> optionalAccount = Optional.ofNullable(accountRepository.getByUsername(username));
        Account account = optionalAccount.orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(account, AccountResponseDto.class);
    }

    public List<AccountResponseDto> getAll() {
        List<Account> accountList = accountRepository.findAll();
        List<AccountResponseDto> accountResponseDtoList = new ArrayList<>();
        for (Account account: accountList) {
            accountResponseDtoList.add(modelMapper.map(account, AccountResponseDto.class));
        }
        return accountResponseDtoList;
    }

    public List<AccountResponseDto> getAllPageable(Pageable pageable) {
        Slice<Account> all = accountRepository.findAll(pageable);
        List<AccountResponseDto> accountResponseDtoList = new ArrayList<>();
//        for (Account account: accountList) {
//            accountDtoList.add(modelMapper.map(account, AccountDto.class));
//        }
        return accountResponseDtoList;
    }

    public AccountResponseDto save(AccountSaveRequestDto accountSaveRequestDto) {
        Account account = modelMapper.map(accountSaveRequestDto, Account.class);
        account = accountRepository.save(account);
        return modelMapper.map(account, AccountResponseDto.class);
    }

    public AccountResponseDto update(String id, AccountSaveRequestDto accountSaveRequestDto) {
        Assert.isNull(id, "Id cannot be null");
        Optional<Account> account = accountRepository.findById(id);
        Account updatedAccount = account.map(it -> {
            it.setName(accountSaveRequestDto.getName());
            it.setSurname(accountSaveRequestDto.getSurname());
            it.setUsername(accountSaveRequestDto.getUsername());
            it.setEmail(accountSaveRequestDto.getEmail());
            return it;
        }).orElseThrow(IllegalArgumentException::new);
        updatedAccount = accountRepository.save(updatedAccount);

        return modelMapper.map(updatedAccount, AccountResponseDto.class);
    }

    public void delete(String id) {
        Account account = accountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        accountRepository.delete(account);
    }
}
