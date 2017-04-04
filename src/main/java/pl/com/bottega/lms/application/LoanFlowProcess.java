package pl.com.bottega.lms.application;

import pl.com.bottega.lms.model.BookId;
import pl.com.bottega.lms.model.ClientId;
import pl.com.bottega.lms.model.numbers.BookIdGenerator;

public interface LoanFlowProcess {

    void loanBook(BookId bookId, ClientId clientId);

    void returnBook(BookId bookId, ClientId clientId);

}
