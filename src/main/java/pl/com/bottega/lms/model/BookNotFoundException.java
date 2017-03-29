package pl.com.bottega.lms.model;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(BookId bookId) {
        super(String.format("Book with ID %s does not exists", bookId.getId()));
    }

}
