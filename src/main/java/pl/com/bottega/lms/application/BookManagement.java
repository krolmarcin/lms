package pl.com.bottega.lms.application;

import pl.com.bottega.lms.model.BookId;
import pl.com.bottega.lms.model.commands.CreateBookCommand;
import pl.com.bottega.lms.model.commands.UpdateBookCommand;

public interface BookManagement {

    BookId create(CreateBookCommand cmd);

    void update(UpdateBookCommand cmd);

    void remove(BookId bookId);

}