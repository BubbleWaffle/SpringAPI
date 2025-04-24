package dev.bublwafl.springapi.controller;

import dev.bublwafl.springapi.DTO.AddAuthorDTO;
import dev.bublwafl.springapi.DTO.AuthorDTO;
import dev.bublwafl.springapi.service.AuthorService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@Validated
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

    @GetMapping("/chunk")
    public Page<AuthorDTO> readChunk(@RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(defaultValue = "3") @Min(1) @Max(3) int size) {
        return authorService.readChunk(page, size);
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