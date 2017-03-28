package pl.com.bottega.lms.application.impl;

import pl.com.bottega.lms.application.LoanFlowProcess;
import pl.com.bottega.lms.model.BookId;
import pl.com.bottega.lms.model.ClientId;
import pl.com.bottega.lms.model.numbers.BookIdGenerator;

public class StandardLoanFlowProcess implements LoanFlowProcess {

    private BookId bookId;
    private ClientId clientId;
    private BookIdGenerator bookIdGenerator;

    public StandardLoanFlowProcess(BookIdGenerator bookIdGenerator, ClientId clientId){
        this.bookIdGenerator = bookIdGenerator;
        this.clientId = clientId;
    }


    @Override
    public void loanBook(BookId bookId, ClientId clientId) {

    }

    @Override
    public void returnBook(BookId bookId) {

    }

}
