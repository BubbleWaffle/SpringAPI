package dev.bublwafl.springapi.DTO;

import java.util.List;
import java.util.Set;

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private Set<String> tags;
    private Double averageRating;
    private List<Long> rateIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public List<Long> getRateIds() {
        return rateIds;
    }

    public void setRateIds(List<Long> rateIds) {
        this.rateIds = rateIds;
    }
}
