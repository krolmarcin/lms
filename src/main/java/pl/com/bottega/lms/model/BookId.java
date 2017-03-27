package pl.com.bottega.lms.model;

public class BookId {

    private String bookId;

    BookId() {
    }

    public BookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookId bookId1 = (BookId) o;

        return bookId.equals(bookId1.bookId);
    }

    @Override
    public int hashCode() {
        return bookId.hashCode();
    }

}
