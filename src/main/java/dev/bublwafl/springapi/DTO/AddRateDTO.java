package dev.bublwafl.springapi.DTO;

public class AddRateDTO {
    private double value;
    private Long bookId;
    private Long readerId;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }
}
