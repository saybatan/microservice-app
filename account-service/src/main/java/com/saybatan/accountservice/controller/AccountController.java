package com.saybatan.accountservice.controller;

import com.saybatan.accountservice.service.AccountService;
import com.saybatan.servicecommon.client.contract.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(accountService.getById(id));
    }

    @GetMapping("/username")
    public ResponseEntity<AccountDto> getByUsername(@RequestParam String username) {
        return ResponseEntity.ok(accountService.getByUsername(username));
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAll() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("/get-all-pageable")
    public ResponseEntity<List<AccountDto>> getAllPageable(Pageable pageable) {
        return ResponseEntity.ok(accountService.getAllPageable(pageable));
    }

    @PostMapping
    public ResponseEntity<AccountDto> save(@RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.save(accountDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> update(@PathVariable("id") String id, @RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.update(id, accountDto));
    }

    @DeleteMapping
    public void delete(String id) {
        accountService.delete(id);
    }
}
