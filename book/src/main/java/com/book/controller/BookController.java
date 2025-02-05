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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

  private final BookService bookService;

  @GetMapping("/{id}")
  private ResponseEntity<List<Book>> getUserBooks(@PathVariable("id") String userId){
    return ResponseEntity.ok(bookService.getUserBooks(userId));
  }

  @PostMapping
  private ResponseEntity<AddBookResponse> addNewBook(AddBookRequest addBookRequest){
    var response = bookService.addBook(addBookRequest);
    return new ResponseEntity<>(response,response.isSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping("/{id}")
  private ResponseEntity<Boolean> removeBookById(@PathVariable("id") String bookId){
    UUID bookUUID = UUID.fromString(bookId);
    bookService.removeBookById(bookUUID);
    return ResponseEntity.ok(true);
  }
}
