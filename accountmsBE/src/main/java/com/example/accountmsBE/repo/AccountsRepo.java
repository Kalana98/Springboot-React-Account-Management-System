package com.example.accountmsBE.repo;

import com.example.accountmsBE.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepo extends JpaRepository<Accounts, Integer> {
}
