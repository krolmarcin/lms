package pl.com.bottega.lms.application;

import pl.com.bottega.lms.model.Book;
import pl.com.bottega.lms.model.ClientId;

public interface LoanFlowProcess {

    void loanBook(Book book, ClientId clientId);

    void returnBook(Book book, ClientId clientId);

}
