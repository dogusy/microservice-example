package com.book.model;

import com.book.common.BookStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {

  @Id
  @UuidGenerator(style = UuidGenerator.Style.RANDOM)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "author", nullable = false)
  private String author;
  @Column(name = "status", nullable = false)
  private BookStatus status;
  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;
  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;



}
