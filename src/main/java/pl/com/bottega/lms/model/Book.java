package pl.com.bottega.lms.model;

import pl.com.bottega.lms.model.commands.CreateBookCommand;
import pl.com.bottega.lms.model.commands.UpdateBookCommand;
import pl.com.bottega.lms.model.numbers.BookIdGenerator;

public class Book {

    private BookId bookId;
    private String title;
    private String year;
    private String author;
    private BookIdGenerator bookIdGenerator;

    Book() {
    }

    public Book(CreateBookCommand cmd) {
        this.bookId = bookIdGenerator.generate();
        this.title = title;
        this.year = year;
        this.author = author;
    }

    public void update(UpdateBookCommand cmd) {

    }

    public void remove(BookId bookId) {

    }

}
