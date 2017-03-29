package pl.com.bottega.lms.model;

import pl.com.bottega.lms.model.commands.CreateBookCommand;
import pl.com.bottega.lms.model.commands.UpdateBookCommand;
import pl.com.bottega.lms.model.numbers.BookIdGenerator;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
public class Book {

    @EmbeddedId
    private BookId id;
    private String title;
    private String year;
    private String author;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    Loan loans;

    Book() {
    }

    public Book(CreateBookCommand cmd, BookIdGenerator bookIdGenerator) {
        this.id = bookIdGenerator.generate();
        this.title = cmd.getTitle();
        this.year = cmd.getYear();
        this.author = cmd.getAuthor();
    }

    public void update(UpdateBookCommand cmd) {
        this.title = cmd.getTitle();
        this.year = cmd.getYear();
        this.author = cmd.getAuthor();
    }

    public BookId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

}
