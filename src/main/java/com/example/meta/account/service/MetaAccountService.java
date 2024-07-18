package com.example.meta.account.service;

import com.example.meta.account.domain.MetaAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;

public interface MetaAccountService {

    MetaAccount save(MetaAccount metaAccount);
    MetaAccount create(MetaAccount metaAccount);
    MetaAccount update(MetaAccount metaAccount);
    void delete(Long id);
    Optional<MetaAccount> read(Long id);
    Page<MetaAccount> list(Pageable pageable);
}
