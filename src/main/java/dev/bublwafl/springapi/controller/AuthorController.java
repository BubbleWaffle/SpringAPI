package dev.bublwafl.springapi.controller;

import dev.bublwafl.springapi.DTO.AddAuthorDTO;
import dev.bublwafl.springapi.DTO.AuthorDTO;
import dev.bublwafl.springapi.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public AuthorDTO create(@RequestBody AddAuthorDTO author) {
        return authorService.create(author);
    }

    @GetMapping("/{id}")
    public AuthorDTO read(@PathVariable Long id) {
        return authorService.read(id);
    }

    @GetMapping("/all")
    public List<AuthorDTO> readAll() {
        return authorService.readAll();
    }

    @PutMapping("/{id}")
    public AuthorDTO update(@RequestBody AddAuthorDTO author, @PathVariable Long id) {
        return authorService.update(author, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}