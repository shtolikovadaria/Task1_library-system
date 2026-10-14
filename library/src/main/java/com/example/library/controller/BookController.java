package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String listBooks(@RequestParam(required = false) String searchTitle,
                            @RequestParam(required = false) String searchPublisher,
                            @RequestParam(required = false) String searchStudent,
                            @RequestParam(required = false, defaultValue = "false") boolean sorted,
                            Model model) {

        String title = emptyToNull(searchTitle);
        String publisher = emptyToNull(searchPublisher);
        String student = emptyToNull(searchStudent);

        List<Book> books;
        if (sorted) {
            books = bookRepository.findAllValidSortedByIssueDate();
        } else if (title != null || publisher != null || student != null) {
            books = bookRepository.searchBooks(title, publisher, student);
        } else {
            books = bookRepository.findAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("newBook", new Book());
        return "index";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        return bookRepository.findById(id)
                .map(book -> {
                    model.addAttribute("book", book);
                    return "edit";
                })
                .orElse("redirect:/books");
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookRepository.save(book); // save() с существующим id выполняет UPDATE
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    private static String emptyToNull(String s) {
        return (s == null || s.isBlank()) ? null : s.trim();
    }
}