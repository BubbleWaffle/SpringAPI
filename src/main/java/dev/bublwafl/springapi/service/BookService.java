package dev.bublwafl.springapi.service;

import dev.bublwafl.springapi.DTO.AddBookDTO;
import dev.bublwafl.springapi.DTO.BookDTO;
import dev.bublwafl.springapi.entity.Author;
import dev.bublwafl.springapi.entity.Book;
import dev.bublwafl.springapi.entity.Rate;
import dev.bublwafl.springapi.entity.Tag;
import dev.bublwafl.springapi.repo.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final TagService tagService;
    private final RateService rateService;
    public BookService(BookRepository bookRepository, AuthorService authorService, TagService tagService, RateService rateService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.tagService = tagService;
        this.rateService = rateService;
    }

    public Book getBookEntityById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    // CRUD
    public BookDTO create(AddBookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());

        Author author = authorService.getAuthorById(dto.getAuthorId());
        book.setAuthor(author);

        if (dto.getTags() != null && !dto.getTags().isEmpty()) {
            Set<Tag> tags = dto.getTags().stream()
                    .map(tagService::create)
                    .map(tagDto -> tagService.getTagByName(tagDto.getName()))
                    .collect(Collectors.toSet());
            book.setTags(tags);
        }

        book = bookRepository.save(book);

        return convertToDTO(book);
    }

    public BookDTO read(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        return convertToDTO(book);
    }

    public List<BookDTO> readAll() {
        return bookRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public BookDTO update(AddBookDTO dto, Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));

        if (dto.getTitle() != null && !dto.getTitle().isEmpty()) {
            book.setTitle(dto.getTitle());
        }

        if (dto.getAuthorId() != null) {
            Author author = authorService.getAuthorById(dto.getAuthorId());
            book.setAuthor(author);
        }

        if (dto.getTags() != null) {
            Set<Tag> tags = dto.getTags().stream()
                    .map(tagService::create)
                    .map(tagDto -> tagService.getTagByName(tagDto.getName()))
                    .collect(Collectors.toSet());
            book.setTags(tags);
        }

        book = bookRepository.save(book);

        return convertToDTO(book);
    }

    public void delete(Long id) {
        if(!bookRepository.existsById(id)) throw new EntityNotFoundException("Book not found");
        bookRepository.deleteById(id);
    }

    private BookDTO convertToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor().getName() + " " + book.getAuthor().getSurname());

        List<Rate> rates = rateService.getRateByBookId(book.getId());

        if (rates != null && !rates.isEmpty()) {
            dto.setAverageRating(rates.stream().mapToDouble(Rate::getValue).sum()/rates.size());
            dto.setRateIds(rates.stream().map(Rate::getId).collect(Collectors.toList()));
        }

        if (book.getTags() != null) {
            Set<String> tagNames = book.getTags().stream()
                    .map(Tag::getName)
                    .collect(Collectors.toSet());
            dto.setTags(tagNames);
        }

        return dto;
    }
}