package dev.bublwafl.springapi.repo;

import dev.bublwafl.springapi.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);

    @Query("SELECT t FROM Tag t WHERE t.books IS EMPTY")
    List<Tag> findUnusedTags();
}