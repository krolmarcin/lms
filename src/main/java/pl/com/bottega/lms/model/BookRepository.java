package pl.com.bottega.lms.model;

public interface BookRepository {

    void put(Book book);

    Book get(BookId bookId);

    void remove(Book book);

}
