package dev.bublwafl.springapi.repo;

import dev.bublwafl.springapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> { }