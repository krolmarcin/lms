package pl.com.bottega.lms.application.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.lms.application.BookManagement;
import pl.com.bottega.lms.model.Book;
import pl.com.bottega.lms.model.BookId;
import pl.com.bottega.lms.model.BookRepository;
import pl.com.bottega.lms.model.commands.CreateBookCommand;
import pl.com.bottega.lms.model.commands.UpdateBookCommand;
import pl.com.bottega.lms.model.numbers.BookIdGenerator;

@Transactional
public class StandardBookManagement implements BookManagement {

    private BookRepository bookRepository;
    private BookIdGenerator bookIdGenerator;

    public StandardBookManagement(BookIdGenerator bookIdGenerator, BookRepository bookRepository) {
        this.bookIdGenerator = bookIdGenerator;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookId create(CreateBookCommand cmd) {
        Book book = new Book(cmd, bookIdGenerator);
        bookRepository.put(book);
        return book.getId();
    }

    @Override
    public void update(UpdateBookCommand cmd) {
        BookId bookId = new BookId(cmd.getId());
        Book book = bookRepository.get(bookId);
        book.update(cmd);
    }

    @Override
    public void remove(BookId bookId) {
        Book book = bookRepository.get(bookId);
        bookRepository.remove(book);
    }

}
