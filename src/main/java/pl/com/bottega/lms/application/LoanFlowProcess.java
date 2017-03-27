package pl.com.bottega.lms.application;

import pl.com.bottega.lms.model.BookId;
import pl.com.bottega.lms.model.ClientId;

public interface LoanFlowProcess {

    void loanBook(BookId bookId, ClientId clientId);

    void returnBook(BookId bookId);

}
