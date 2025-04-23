package dev.bublwafl.springapi.controller;

import dev.bublwafl.springapi.DTO.AddBookDTO;
import dev.bublwafl.springapi.DTO.BookDTO;
import dev.bublwafl.springapi.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookDTO create(@RequestBody AddBookDTO book) {
        return bookService.create(book);
    }

    @GetMapping("/{id}")
    public BookDTO read(@PathVariable Long id) {
        return bookService.read(id);
    }

    @GetMapping("/all")
    public List<BookDTO> readAll() {
        return bookService.readAll();
    }

    @PutMapping("/{id}")
    public BookDTO update(@RequestBody AddBookDTO book, @PathVariable Long id) {
        return bookService.update(book, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}