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
    var book = Book.builder().
        author(addBookRequest.getAuthor()).
        name(addBookRequest.getBookName()).
        createdAt(OffsetDateTime.now()).
        status(BookStatus.NEW).build();
    var addBookResponse = AddBookResponse.builder().
        bookName(addBookRequest.getBookName()).
        author(addBookRequest.getAuthor()).
        msg("");
        if (bookRepository.save(book).getId()!= null){
          addBookResponse.success(true);
        }else {
          addBookResponse.success(false);
        }
        return addBookResponse.build();

  }

  public void removeBookById(UUID bookId) {
    bookRepository.deleteById(bookId);
  }
}
