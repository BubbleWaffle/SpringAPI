package dev.bublwafl.springapi.controller;

import dev.bublwafl.springapi.DTO.AddReaderDTO;
import dev.bublwafl.springapi.DTO.ReaderDTO;
import dev.bublwafl.springapi.service.ReaderService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reader")
@Validated
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

    @GetMapping("/chunk")
    public Page<ReaderDTO> readChunk(@RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(defaultValue = "3") @Min(1) @Max(3) int size) {
        return readerService.readChunk(page, size);
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