package dev.bublwafl.springapi.service;

import dev.bublwafl.springapi.DTO.AddReaderDTO;
import dev.bublwafl.springapi.DTO.ReaderDTO;
import dev.bublwafl.springapi.entity.Reader;
import dev.bublwafl.springapi.repo.ReaderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
    @Transactional
    public ReaderDTO create(AddReaderDTO dto) {

        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new EntityNotFoundException("Name is required");
        }

        if (dto.getSurname() == null || dto.getSurname().isEmpty()) {
            throw new EntityNotFoundException("Surname is required");
        }

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

    @Transactional
    public ReaderDTO update(AddReaderDTO dto, Long id) {
        Reader reader = readerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reader not found"));

        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new EntityNotFoundException("Name is required");
        }

        if (dto.getSurname() == null || dto.getSurname().isEmpty()) {
            throw new EntityNotFoundException("Surname is required");
        }

        reader = readerRepository.save(reader);
        return modelMapper.map(reader, ReaderDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        if(!readerRepository.existsById(id)) throw new EntityNotFoundException("Reader not found");
        readerRepository.deleteById(id);
    }
}