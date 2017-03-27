package pl.com.bottega.lms.model.numbers;

import pl.com.bottega.lms.model.BookId;

import java.util.UUID;

public class ISBNIdGenerator implements BookIdGenerator {

    @Override
    public BookId generate() {
        return new BookId("ISBN-" + UUID.randomUUID().toString());
    }

}
