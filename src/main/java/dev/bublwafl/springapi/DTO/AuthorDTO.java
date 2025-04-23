package dev.bublwafl.springapi.DTO;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {
    private Long id;
    private String name;
    private String surname;
    private int no_books;
    private List<Long> bookIds = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNo_books() {
        return no_books;
    }

    public void setNo_books(int no_books) {
        this.no_books = no_books;
    }

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBooks(List<Long> bookIds) {
        this.bookIds = bookIds;
    }
}