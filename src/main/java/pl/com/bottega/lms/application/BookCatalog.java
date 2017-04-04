package pl.com.bottega.lms.application;

import pl.com.bottega.lms.application.dtos.BookDto;
import pl.com.bottega.lms.model.BookId;

public interface BookCatalog {

    BookSearchResults find(BookQuery bookQuery);

    BookDto get(BookId bookId);

}
