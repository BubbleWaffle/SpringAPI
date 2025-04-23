package dev.bublwafl.springapi.scheduled;

import dev.bublwafl.springapi.entity.Tag;
import dev.bublwafl.springapi.repo.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagWatcher {
    private final TagRepository tagRepository;

    @Autowired
    public TagWatcher(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Scheduled(fixedDelay = 300000)
    public void deleteUnusedTags() {
        List<Tag> unusedTags = tagRepository.findUnusedTags();

        if (!unusedTags.isEmpty()) {
            tagRepository.deleteAll(unusedTags);
            System.out.println("Deleted " + unusedTags.size() + " unused tags");
        }
    }
}