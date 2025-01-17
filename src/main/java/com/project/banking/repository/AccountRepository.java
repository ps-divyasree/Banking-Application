package com.project.banking.repository;

import com.project.banking.entity.Account;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
