package dev.bublwafl.springapi.repo;

import dev.bublwafl.springapi.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {
    boolean existsByBookIdAndReaderId(Long bookId, Long readerId);
    List<Rate> findByBookId(Long bookId);
}