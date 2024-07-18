package com.example.meta.account.service;

import com.example.meta.account.domain.MetaAccount;
import com.example.meta.account.repository.MetaAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MetaAccountServiceImpl implements MetaAccountService {

    private final Logger log = LoggerFactory.getLogger(MetaAccountServiceImpl.class);
    private final MetaAccountRepository metaAccountRepository;

    public MetaAccountServiceImpl(MetaAccountRepository metaAccountRepository) {
        this.metaAccountRepository = metaAccountRepository;
    }

    @Override
    public MetaAccount save(MetaAccount metaAccount) {
        log.debug("Request to save metaAccount : {}", metaAccount);
        return metaAccountRepository.save(metaAccount);
    }

    @Override
    public MetaAccount create(MetaAccount metaAccount) {
        log.debug("Request to create metaAccount : {}", metaAccount);
        return metaAccountRepository.save(metaAccount);
    }

    @Override
    public MetaAccount update(MetaAccount metaAccount) {
        log.debug("Request to update metaAccount : {}", metaAccount);
        return metaAccountRepository.save(metaAccount);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete metaAccount : {}", id);
        metaAccountRepository.deleteById(id);
    }

    @Override
    public Optional<MetaAccount> read(Long id) {
        log.debug("Request to read metaAccount : {}", id);
        return metaAccountRepository.findById(id);
    }

    @Override
    public Page<MetaAccount> list(Pageable pageable) {
        log.debug("Request to get all MetaAccount");
        return metaAccountRepository.findAll(pageable);
    }
}
