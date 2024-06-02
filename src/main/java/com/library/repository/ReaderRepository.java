package com.library.repository;

import com.library.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {


    Optional<Reader> findReaderById(Long id);

    @Query("select r from Reader r left join fetch r.books where r.id = :id")
    Optional<Reader> findReaderByIdFetch(Long id);

}
