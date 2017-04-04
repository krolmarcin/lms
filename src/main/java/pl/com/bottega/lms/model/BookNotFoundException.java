package pl.com.bottega.lms.model;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String msg) {
        super(msg);
    }

    public BookNotFoundException(BookId bookId) {
        super(String.format("Book with ID %s does not exists", bookId.getId()));
    }

    public BookNotFoundException(String format, String bookId, String clientId) {
        super();
    }
}
