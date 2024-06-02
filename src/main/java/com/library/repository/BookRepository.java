package com.library.repository;

import com.library.model.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b left join fetch b.reader order by b.id")
    List<Book> findAllFetch();

    @Query("select b from Book b left join fetch b.reader where b.id = :bookId order by b.id")
    Optional<Book> findBookByIdFetch(Long bookId);

    @Query("select b from Book b left join fetch b.reader r where r.id is not null")
    List<Book> findBorrowedBooksOrderById(Sort sort);

    @Query("select b from Book b left join fetch b.reader r where r.id is null")
    List<Book> findFreeBooksOrderById(Sort sort);

}