package com.library.service;

import com.library.dto.ReaderUpdateRequest;
import com.library.model.Reader;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ReaderService {

    Long save(Reader reader);

    List<Reader> findAll(Sort sort);

    default List<Reader> findAll() {
        return findAll(Sort.by("id"));
    }

    Reader findById(Long id);

    Reader findByIdFetch(Long id);

    void update(Long id, ReaderUpdateRequest request);

    void delete(Long id);

}
