package dev.bublwafl.springapi.controller;

import dev.bublwafl.springapi.DTO.AddTagDTO;
import dev.bublwafl.springapi.DTO.TagDTO;
import dev.bublwafl.springapi.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
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

    @GetMapping("/all")
    public List<TagDTO> readAll() {
        return tagService.readAll();
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