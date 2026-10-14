package com.example.library.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "publisher")
    private String издательство;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issueDate;

    private String studentName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
}