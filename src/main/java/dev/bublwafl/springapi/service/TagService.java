package dev.bublwafl.springapi.service;

import dev.bublwafl.springapi.DTO.AddTagDTO;
import dev.bublwafl.springapi.DTO.TagDTO;
import dev.bublwafl.springapi.entity.Book;
import dev.bublwafl.springapi.entity.Tag;
import dev.bublwafl.springapi.repo.TagRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;
    public TagService(TagRepository tagRepository, ModelMapper modelMapper) {
        this.tagRepository = tagRepository;
        this.modelMapper = modelMapper;
    }

    public Tag getTagByName(String name) {
        return tagRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Tag not found"));
    }

    // CRUD
    @Transactional
    public TagDTO create(AddTagDTO dto) {
        Optional<Tag> existingTag = tagRepository.findByName(dto.getName());

        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new EntityNotFoundException("Tag name is required");
        }

        if (existingTag.isPresent()) return modelMapper.map(existingTag.get(), TagDTO.class);

        Tag tag = modelMapper.map(dto, Tag.class);
        tag = tagRepository.save(tag);

        return modelMapper.map(tag, TagDTO.class);
    }

    public TagDTO read(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag not found"));
        return mapTagToDTO(tag);
    }

    public List<TagDTO> readAll() {
        return tagRepository.findAll().stream().map(this::mapTagToDTO).collect(Collectors.toList());
    }

    @Transactional
    public TagDTO update(AddTagDTO dto, Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag not found"));

        Optional<Tag> existingTag = tagRepository.findByName(dto.getName());

        if (existingTag.isPresent()) throw new EntityExistsException("Tag already exists");

        tag.setName(dto.getName());
        tag = tagRepository.save(tag);

        return modelMapper.map(tag, TagDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag not found"));

        for (Book book : tag.getBooks()) {
            book.getTags().remove(tag);
        }

        tag.getBooks().clear();

        tagRepository.deleteById(id);
    }

    private TagDTO mapTagToDTO(Tag tag) {
        TagDTO dto = new TagDTO();
        dto.setId(tag.getId());
        dto.setName(tag.getName());
        dto.setBookIds(tag.getBooks().stream().map(Book::getId).collect(Collectors.toSet()));

        return dto;
    }
}