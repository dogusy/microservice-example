package com.book.repository;

import com.book.model.Book;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

  public Optional<List<Book>> getUserBooks(String userId);
}
