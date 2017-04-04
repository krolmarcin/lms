package pl.com.bottega.lms.application;

import pl.com.bottega.lms.application.dtos.BookDto;

import java.util.List;

public class BookSearchResults {

    private List<BookDto> books;

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }

    public List<BookDto> getBooks() {
        return books;
    }

}
