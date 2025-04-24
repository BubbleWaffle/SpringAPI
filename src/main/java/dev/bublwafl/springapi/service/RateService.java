package dev.bublwafl.springapi.service;

import dev.bublwafl.springapi.DTO.AddRateDTO;
import dev.bublwafl.springapi.DTO.RateDTO;
import dev.bublwafl.springapi.DTO.UpdateRateDTO;
import dev.bublwafl.springapi.entity.Book;
import dev.bublwafl.springapi.entity.Rate;
import dev.bublwafl.springapi.entity.Reader;
import dev.bublwafl.springapi.repo.RateRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService {
    private final RateRepository rateRepository;
    private final ModelMapper modelMapper;
    private final BookService bookService;
    private final ReaderService readerService;
    public RateService(RateRepository rateRepository, ModelMapper modelMapper, @Lazy BookService bookService, ReaderService readerService) {
        this.rateRepository = rateRepository;
        this.modelMapper = modelMapper;
        this.bookService = bookService;
        this.readerService = readerService;
    }

    public List<Rate> getRateByBookId(Long bookId) {
        return rateRepository.findByBookId(bookId);
    }

    // CRUD
    @Transactional
    public RateDTO create(AddRateDTO dto) {
        Book book = bookService.getBookEntityById(dto.getBookId());
        Reader reader = readerService.getReaderEntityById(dto.getReaderId());

        if (rateRepository.existsByBookIdAndReaderId(dto.getBookId(), dto.getReaderId())) throw new IllegalStateException("Reader has already rated this book");

        if (dto.getValue() < 1 || dto.getValue() > 5) throw new IllegalArgumentException("Rating must be between 1 and 5");

        Rate rate = new Rate();
        rate.setValue(dto.getValue());
        rate.setBook(book);
        rate.setReader(reader);

        rate = rateRepository.save(rate);
        return modelMapper.map(rate, RateDTO.class);
    }

    public RateDTO read(Long id) {
        Rate rate = rateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rate not found"));
        return modelMapper.map(rate, RateDTO.class);
    }

    public Page<RateDTO> readChunk(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return rateRepository.findAll(pageable).map(rate -> modelMapper.map(rate, RateDTO.class));
    }

    @Transactional
    public RateDTO update(UpdateRateDTO dto, Long id) {
        Rate rate = rateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rate not found"));

        if (dto.getValue() < 1 || dto.getValue() > 5) throw new IllegalArgumentException("Rating must be between 1 and 5");

        rate.setValue(dto.getValue());
        rate = rateRepository.save(rate);

        return modelMapper.map(rate, RateDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        if(!rateRepository.existsById(id)) throw new EntityNotFoundException("Rate not found");
        rateRepository.deleteById(id);
    }
}