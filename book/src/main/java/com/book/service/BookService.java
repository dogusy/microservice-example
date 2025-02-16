package com.book.service;

import com.book.common.BookStatus;
import com.book.dto.request.AddBookRequest;
import com.book.dto.response.AddBookResponse;
import com.book.model.Book;
import com.book.repository.BookRepository;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  public List<Book> getUserBooks(String userId) {
    return bookRepository.getUserBooks(userId).orElse(new ArrayList<>());
  }

  public AddBookResponse addBook(AddBookRequest addBookRequest) {
    try {
      var book = Book.builder().
              author(addBookRequest.getAuthor()).
              name(addBookRequest.getBookName()).
              createdAt(OffsetDateTime.now()).
              status(BookStatus.NEW).build();

      var savedBook = bookRepository.save(book);

      var addBookResponse = AddBookResponse.builder().
              bookName(savedBook.getName()).
              author(savedBook.getAuthor()).
              msg("Save Success").
              success(true).build();

      return addBookResponse;
    }catch (Exception e){
      //TODO build global exceiption handler and define exceptions if necc.
      throw new RuntimeException();
    }
  }

  public void removeBookById(UUID bookId) {
    try {
      bookRepository.deleteById(bookId);
    }catch (Exception e){
      throw new RuntimeException();
    }
  }
}
