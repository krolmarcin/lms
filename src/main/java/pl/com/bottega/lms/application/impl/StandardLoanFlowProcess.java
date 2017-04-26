package pl.com.bottega.lms.application.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.lms.application.LoanFlowProcess;
import pl.com.bottega.lms.model.*;
import pl.com.bottega.lms.model.numbers.BookIdGenerator;

@Transactional
public class StandardLoanFlowProcess implements LoanFlowProcess {

    private BookRepository bookRepository;
    private ClientRepository clientRepository;
    private LoanRepository loanRepository;

    public StandardLoanFlowProcess(LoanRepository loanRepository, BookRepository bookRepository, ClientRepository clientRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void loanBook(Book book, ClientId clientId) {
        if (!book.isAvailable())
            throw new BookNotFoundException(String.format("Book %s has already lent for Client %s", book.getId(), clientId.getId().toString()));
        else {
            book.setAvailable(false);
            Loan loan = new Loan(book, clientId);
            loan.setActive(true);
            loanRepository.put(loan);
        }
    }

    @Override
    public void returnBook(Book book, ClientId clientId) {
        book.setAvailable(true);
        Loan loan = loanRepository.getActiveForBookIdAndClientId(book.getId(), clientId);
        loan.setActive(false);
        loan.returnBook(book, clientId);
    }

}
