package com.example.meta.account.repository;

import com.example.meta.account.domain.MetaAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
public interface MetaAccountRepository extends JpaRepository<MetaAccount,Long> {
}
