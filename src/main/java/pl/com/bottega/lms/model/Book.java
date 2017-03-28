package pl.com.bottega.lms.model;

import pl.com.bottega.lms.model.commands.CreateBookCommand;
import pl.com.bottega.lms.model.commands.UpdateBookCommand;
import pl.com.bottega.lms.model.numbers.BookIdGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BOOKS")
public class Book {

    @EmbeddedId
    private BookId bookId;
    private String title;
    private String year;
    private String author;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookId")
    Set<Loan> loans;

    Book() {
    }

    public Book(CreateBookCommand cmd, BookIdGenerator bookIdGenerator) {
        this.bookId = bookIdGenerator.generate();
        this.title = cmd.getTitle();
        this.year = cmd.getYear();
        this.author = cmd.getAuthor();
    }

    public void update(UpdateBookCommand cmd) {
        this.title = cmd.getTitle();
        this.year = cmd.getYear();
        this.author = cmd.getAuthor();
    }

    public void remove(BookId bookId) {

    }

    public BookId getBookId() {
        return bookId;
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
