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
    public void loanBook(BookId bookId, ClientId clientId) {
        Book book = bookRepository.get(bookId);
        if (!book.isAvailable())
            throw new BookNotFoundException(String.format("Book %s has already lent for Client %s", bookId.getId(), clientId.getId().toString()));
        else {
            book.setAvailable(false);
            Loan loan = new Loan(bookId, clientId);
            loan.setActive(true);
            loanRepository.put(loan);
        }
    }

    @Override
    public void returnBook(BookId bookId, ClientId clientId) {
        Book book = bookRepository.get(bookId);
        book.setAvailable(true);
        Loan loan = loanRepository.getActiveForBookIdAndClientId(bookId, clientId);
        loan.setActive(false);
        loan.returnBook(bookId, clientId);
    }

}
