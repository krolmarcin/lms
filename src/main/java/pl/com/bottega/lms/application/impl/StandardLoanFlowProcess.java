package pl.com.bottega.lms.application.impl;

import pl.com.bottega.lms.application.LoanFlowProcess;
import pl.com.bottega.lms.model.*;
import pl.com.bottega.lms.model.numbers.BookIdGenerator;

public class StandardLoanFlowProcess implements LoanFlowProcess {

    private BookId bookId;
    private ClientId clientId;
    private BookIdGenerator bookIdGenerator;
    private Book book;
    private BookRepository bookRepository;
    private ClientRepository clientRepository;
    private LoanRepository loanRepository;

    public StandardLoanFlowProcess(LoanRepository loanRepository, BookRepository bookRepository, ClientRepository clientRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void loanBook(BookId bookId, ClientId clientId) {
        Book book = bookRepository.get(bookId);
        book.setAvailable(false);
        Loan loan = new Loan(bookId, clientId);
        loanRepository.put(loan);
    }

    @Override
    public void returnBook(BookId bookId) {
        Book book = bookRepository.get(bookId);
        book.setAvailable(true);
        Loan loan = loanRepository.getForBookId(bookId);
        loan.returnBook(bookId);
    }

}
