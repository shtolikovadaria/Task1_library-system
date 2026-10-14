package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title IS NOT NULL AND TRIM(b.title) != '' ORDER BY b.issueDate ASC")
    List<Book> findAllValidSortedByIssueDate();

    @Query("SELECT b FROM Book b WHERE " +
            "(:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:publisher IS NULL OR LOWER(b.издательство) LIKE LOWER(CONCAT('%', :publisher, '%'))) AND " +
            "(:student IS NULL OR LOWER(b.studentName) LIKE LOWER(CONCAT('%', :student, '%')))")
    List<Book> searchBooks(@Param("title") String title,
                           @Param("publisher") String publisher,
                           @Param("student") String student);
}