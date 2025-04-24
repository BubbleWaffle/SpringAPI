package dev.bublwafl.springapi.controller;

import dev.bublwafl.springapi.DTO.AddBookDTO;
import dev.bublwafl.springapi.DTO.BookDTO;
import dev.bublwafl.springapi.service.BookService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@Validated
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

    @GetMapping("/chunk")
    public Page<BookDTO> readChunk(@RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(defaultValue = "3") @Min(1) @Max(3) int size) {
        return bookService.readChunk(page, size);
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