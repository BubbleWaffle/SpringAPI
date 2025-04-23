package dev.bublwafl.springapi.DTO;

import java.util.Set;

public class AddBookDTO {
    private String title;
    private Long authorId;
    private Set<AddTagDTO> tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Set<AddTagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<AddTagDTO> tags) {
        this.tags = tags;
    }
}