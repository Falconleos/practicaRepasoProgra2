package model;

import enums.MaterialStatus;
import interfaces.Loanable;

public class Book extends Material implements Loanable {

    private String author;

    public Book(String title, Integer publishedYear, MaterialStatus materialStatus, String author) {
        super(title, publishedYear, materialStatus);
        this.author = author;
    }

    public Book() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book:" +
                "\nauthor='" + author +
                "\n";
    }

    @Override
    public void loan() {
        setMaterialStatus(MaterialStatus.LOANED);
    }

    @Override
    public void returnItem() {
        setMaterialStatus(MaterialStatus.AVAILABLE);
    }
}
