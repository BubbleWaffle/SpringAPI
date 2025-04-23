package dev.bublwafl.springapi.service;

import dev.bublwafl.springapi.DTO.AddTagDTO;
import dev.bublwafl.springapi.DTO.TagDTO;
import dev.bublwafl.springapi.entity.Tag;
import dev.bublwafl.springapi.repo.TagRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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
    public TagDTO create(AddTagDTO dto) {
        Optional<Tag> existingTag = tagRepository.findByName(dto.getName());

        if (existingTag.isPresent()) return modelMapper.map(existingTag.get(), TagDTO.class);

        Tag tag = modelMapper.map(dto, Tag.class);
        tag = tagRepository.save(tag);

        return modelMapper.map(tag, TagDTO.class);
    }

    public TagDTO read(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag not found"));
        return modelMapper.map(tag, TagDTO.class);
    }

    public List<TagDTO> readAll() {
        return tagRepository.findAll().stream().map(tag -> modelMapper.map(tag, TagDTO.class)).collect(Collectors.toList());
    }

    public TagDTO update(AddTagDTO dto, Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag not found"));

        Optional<Tag> existingTag = tagRepository.findByName(dto.getName());

        if (existingTag.isPresent()) throw new EntityExistsException("Tag with name " + dto.getName() + " already exists");

        tag.setName(dto.getName());
        tag = tagRepository.save(tag);

        return modelMapper.map(tag, TagDTO.class);
    }

    public void delete(Long id) {
        if(!tagRepository.existsById(id)) throw new EntityNotFoundException("Tag not found");
        tagRepository.deleteById(id);
    }
}