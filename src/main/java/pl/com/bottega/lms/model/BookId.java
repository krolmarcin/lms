package pl.com.bottega.lms.model;

import java.io.Serializable;

public class BookId implements Serializable {

    private String id;

    BookId() {
    }

    public BookId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookId bookId1 = (BookId) o;

        return id.equals(bookId1.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
