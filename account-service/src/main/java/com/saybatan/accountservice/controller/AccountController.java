package com.saybatan.accountservice.controller;

import com.saybatan.accountservice.dto.AccountSaveRequestDto;
import com.saybatan.accountservice.service.AccountService;
import com.saybatan.servicecommon.client.contract.AccountResponseDto;
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
    public ResponseEntity<AccountResponseDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(accountService.getById(id));
    }

    @GetMapping("/username")
    public ResponseEntity<AccountResponseDto> getByUsername(@RequestParam String username) {
        return ResponseEntity.ok(accountService.getByUsername(username));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> getAll() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("/get-all-pageable")
    public ResponseEntity<List<AccountResponseDto>> getAllPageable(Pageable pageable) {
        return ResponseEntity.ok(accountService.getAllPageable(pageable));
    }

    @PostMapping
    public ResponseEntity<AccountResponseDto> save(@RequestBody AccountSaveRequestDto accountSaveRequestDto) {
        return ResponseEntity.ok(accountService.save(accountSaveRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDto> update(@PathVariable("id") String id, @RequestBody AccountSaveRequestDto accountSaveRequestDto) {
        return ResponseEntity.ok(accountService.update(id, accountSaveRequestDto));
    }

    @DeleteMapping
    public void delete(String id) {
        accountService.delete(id);
    }
}
