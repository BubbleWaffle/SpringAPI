package dev.bublwafl.springapi.controller;

import dev.bublwafl.springapi.DTO.AddReaderDTO;
import dev.bublwafl.springapi.DTO.ReaderDTO;
import dev.bublwafl.springapi.service.ReaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {
    private final ReaderService readerService;
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping
    public ReaderDTO create(@RequestBody AddReaderDTO reader) {
        return readerService.create(reader);
    }

    @GetMapping("/{id}")
    public ReaderDTO read(@PathVariable Long id) {
        return readerService.read(id);
    }

    @GetMapping("/all")
    public List<ReaderDTO> readAll() {
        return readerService.readAll();
    }

    @PutMapping("/{id}")
    public ReaderDTO update(@RequestBody AddReaderDTO reader, @PathVariable Long id) {
        return readerService.update(reader, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        readerService.delete(id);
    }
}