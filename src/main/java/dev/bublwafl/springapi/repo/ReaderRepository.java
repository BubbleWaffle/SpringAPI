package dev.bublwafl.springapi.repo;

import dev.bublwafl.springapi.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, Long> { }