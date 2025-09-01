package org.example.financetracker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.financetracker.entity.BankAccount;
import org.example.financetracker.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;


    @Transactional
    public Optional<BankAccount> findById(Long accountId) {
        return bankAccountRepository.findById(accountId);
    }
}
