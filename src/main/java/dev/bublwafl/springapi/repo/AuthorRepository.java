package dev.bublwafl.springapi.repo;

import dev.bublwafl.springapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> { }