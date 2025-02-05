package com.book.controller;

import com.book.dto.request.AddBookRequest;
import com.book.dto.response.AddBookResponse;
import com.book.model.Book;
import com.book.service.BookService;
import com.thoughtworks.xstream.converters.basic.UUIDConverter;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

  private final BookService bookService;

  @GetMapping("/{id}")
  public ResponseEntity<List<Book>> getUserBooks(@PathVariable("id") String userId) {
    return ResponseEntity.ok(bookService.getUserBooks(userId));
  }

  @PostMapping
  public ResponseEntity<AddBookResponse> addNewBook(@RequestBody AddBookRequest addBookRequest) {
    var response = bookService.addBook(addBookRequest);
    return response.isSuccess()
        ? ResponseEntity.status(HttpStatus.CREATED).body(response)
        : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> removeBookById(@PathVariable("id") UUID bookId) {
    bookService.removeBookById(bookId);
    return ResponseEntity.noContent().build(); // 204 No Content for successful deletions
  }
}
