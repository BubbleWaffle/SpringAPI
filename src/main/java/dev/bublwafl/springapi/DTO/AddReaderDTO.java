package dev.bublwafl.springapi.DTO;

import dev.bublwafl.springapi.enumerate.Sex;

public class AddReaderDTO {
    private String name;
    private String surname;
    private Sex sex;

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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}