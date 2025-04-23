package dev.bublwafl.springapi.service;

import dev.bublwafl.springapi.DTO.AddReaderDTO;
import dev.bublwafl.springapi.DTO.ReaderDTO;
import dev.bublwafl.springapi.entity.Reader;
import dev.bublwafl.springapi.repo.ReaderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final ModelMapper modelMapper;
    public ReaderService(ReaderRepository readerRepository, ModelMapper modelMapper) {
        this.readerRepository = readerRepository;
        this.modelMapper = modelMapper;
    }

    public Reader getReaderEntityById(Long id) {
        return readerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reader not found"));
    }

    // CRUD
    public ReaderDTO create(AddReaderDTO dto) {
        Reader reader = modelMapper.map(dto, Reader.class);
        reader = readerRepository.save(reader);
        return modelMapper.map(reader, ReaderDTO.class);
    }

    public ReaderDTO read(Long id) {
        Reader reader = readerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reader not found"));

        return modelMapper.map(reader, ReaderDTO.class);
    }

    public List<ReaderDTO> readAll() {
        return readerRepository.findAll().stream().map(read -> modelMapper.map(read, ReaderDTO.class)).collect(Collectors.toList());
    }

    public ReaderDTO update(AddReaderDTO dto, Long id) {
        Reader reader = readerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reader not found"));
        reader = readerRepository.save(reader);
        return modelMapper.map(reader, ReaderDTO.class);
    }

    public void delete(Long id) {
        if(!readerRepository.existsById(id)) throw new EntityNotFoundException("Reader not found");
        readerRepository.deleteById(id);
    }
}