package dev.bublwafl.springapi.service;

import dev.bublwafl.springapi.DTO.AddAuthorDTO;
import dev.bublwafl.springapi.DTO.AuthorDTO;
import dev.bublwafl.springapi.entity.Author;
import dev.bublwafl.springapi.entity.Book;
import dev.bublwafl.springapi.repo.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;
    public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Author not found"));
    }

    // CRUD
    public AuthorDTO create(AddAuthorDTO dto) {
        Author author = modelMapper.map(dto, Author.class);
        author = authorRepository.save(author);

        return modelMapper.map(author, AuthorDTO.class);
    }

    public AuthorDTO read(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Author not found"));

        return mapAuthorToDTO(author);
    }

    public List<AuthorDTO> readAll() {
        return authorRepository.findAll().stream()
                .map(this::mapAuthorToDTO)
                .toList();
    }

    public AuthorDTO update(AddAuthorDTO dto, Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Author not found"));

        author.setName(dto.getName());
        author.setSurname(dto.getSurname());
        author = authorRepository.save(author);

        AuthorDTO authorDTO = modelMapper.map(author, AuthorDTO.class);
        authorDTO.setNo_books(author.getBooks().size());

        return authorDTO;
    }

    public void delete(Long id) {
        if(!authorRepository.existsById(id)) throw new EntityNotFoundException("Author not found");
        authorRepository.deleteById(id);
    }

    private AuthorDTO mapAuthorToDTO(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setSurname(author.getSurname());
        dto.setNo_books(author.getBooks().size());
        dto.setBooks(author.getBooks().stream()
                .map(Book::getId)
                .toList());

        return dto;
    }
}