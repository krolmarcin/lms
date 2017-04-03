package pl.com.bottega.lms.infrastructure;

import pl.com.bottega.lms.model.Book;
import pl.com.bottega.lms.model.BookId;
import pl.com.bottega.lms.model.BookNotFoundException;
import pl.com.bottega.lms.model.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPABookRepository implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Book book) {
        entityManager.persist(book);
    }

    @Override
    public void remove(Book book) {
        if (!book.isAvailable())
            throw new BookNotFoundException(String.format("Book %s is not available", book.getId().getId()));
        entityManager.remove(book);
    }

    @Override
    public Book get(BookId bookId) {
        Book book = entityManager.find(Book.class, bookId);
        if (book == null)
            throw new BookNotFoundException(bookId);
        return entityManager.find(Book.class, bookId);
    }

}
