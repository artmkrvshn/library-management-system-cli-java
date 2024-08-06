package com.library.service.impl;

import com.library.dto.ReaderUpdateRequest;
import com.library.exception.ReaderNotFoundException;
import com.library.model.Reader;
import com.library.repository.ReaderRepository;
import com.library.service.ReaderService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository repository;

    public ReaderServiceImpl(ReaderRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Long save(Reader reader) {
        return repository.save(reader).getId();
    }

    @Override
    public List<Reader> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Reader> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Reader findById(Long id) {
        return repository.findReaderById(id).orElseThrow(() -> new ReaderNotFoundException(id));
    }

    @Override
    public Reader findByIdFetch(Long id) {
        return repository.findReaderByIdFetch(id).orElseThrow(() -> new ReaderNotFoundException(id));
    }

    @Transactional
    @Override
    public void update(Long id, ReaderUpdateRequest request) {
        Reader reader = repository.findReaderByIdFetch(id).orElseThrow(() -> new ReaderNotFoundException(id));
        if (request.name() != null) {
            reader.setName(request.name());
        }
        if (request.email() != null) {
            reader.setEmail(request.email());
        }
        if (request.birthday() != null) {
            reader.setBirthday(request.birthday());
        }
        repository.save(reader);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}