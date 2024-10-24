package com.project.banking.service.impl;

import com.project.banking.dto.AccountDto;
import com.project.banking.entity.Account;
import com.project.banking.repository.AccountRepository;
import com.project.banking.service.AccountService;
import org.springframework.stereotype.Service;

import static com.project.banking.mapper.AccountMapper.mapToAccount;
import static com.project.banking.mapper.AccountMapper.mapToAccountDto;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(
                ()-> new RuntimeException("Account does not exit")
                );
        return mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(
                        ()-> new RuntimeException("Account does not exit")
                );
        double totalAmount=account.getBalance()+amount;
        account.setBalance(totalAmount);
        Account latestDetails = accountRepository.save(account);
        return mapToAccountDto(latestDetails);
    }
}
