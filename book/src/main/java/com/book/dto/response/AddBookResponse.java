package com.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBookResponse {

  private String bookName;
  private String author;
  private boolean success;
  private String msg;

}
