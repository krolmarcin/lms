package pl.com.bottega.lms.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.bottega.lms.application.BookCatalog;
import pl.com.bottega.lms.application.ClientCatalog;
import pl.com.bottega.lms.application.LoanFlowProcess;
import pl.com.bottega.lms.application.impl.StandardLoanFlowProcess;
import pl.com.bottega.lms.model.*;
import pl.com.bottega.lms.model.numbers.BookIdGenerator;
import pl.com.bottega.lms.model.numbers.ISBNIdGenerator;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public LoanFlowProcess loanFlowProcess(LoanRepository loanRepository, BookRepository bookRepository, ClientRepository clientRepository) {
        return new StandardLoanFlowProcess(loanRepository, bookRepository, clientRepository);
    }

    @Bean
    public BookIdGenerator bookIdGenerator() {
        return new ISBNIdGenerator();
    }

    @Bean
    public BookCatalog bookCatalog() {
        return new JPABookCatalog();
    }

    @Bean
    public ClientCatalog clientCatalog() {
        return new JPAClientCatalog();
    }

    @Bean
    public BookRepository bookRepository() {
        return new JPABookRepository();
    }

    @Bean
    public ClientRepository clientRepository() {
        return new JPAClientRepository();
    }

    @Bean
    public LoanRepository loanRepository() {
        return new JPALoanRepository();
    }

}
