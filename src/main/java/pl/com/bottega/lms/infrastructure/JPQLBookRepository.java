package pl.com.bottega.lms.infrastructure;

import pl.com.bottega.lms.model.Book;
import pl.com.bottega.lms.model.BookId;
import pl.com.bottega.lms.model.BookRepository;

public class JPQLBookRepository implements BookRepository {
    @Override
    public void put(Book book) {

    }

    @Override
    public Book get(BookId bookId) {
        return null;
    }

}
