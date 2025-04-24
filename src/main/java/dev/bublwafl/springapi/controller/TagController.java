package dev.bublwafl.springapi.controller;

import dev.bublwafl.springapi.DTO.AddTagDTO;
import dev.bublwafl.springapi.DTO.TagDTO;
import dev.bublwafl.springapi.service.TagService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
@Validated
public class TagController {
    private final TagService tagService;
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public TagDTO create(@RequestBody AddTagDTO tag) {
        return tagService.create(tag);
    }

    @GetMapping("/{id}")
    public TagDTO read(@PathVariable Long id) {
        return tagService.read(id);
    }

    @GetMapping("/chunk")
    public Page<TagDTO> readChunk(@RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(defaultValue = "3") @Min(1) @Max(3) int size) {
        return tagService.readChunk(page, size);
    }

    @PutMapping("/{id}")
    public TagDTO update(@RequestBody AddTagDTO tag, @PathVariable Long id) {
        return tagService.update(tag, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tagService.delete(id);
    }
}