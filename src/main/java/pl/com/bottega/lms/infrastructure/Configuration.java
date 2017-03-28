package pl.com.bottega.lms.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.bottega.lms.application.BookCatalog;
import pl.com.bottega.lms.application.LoanFlowProcess;
import pl.com.bottega.lms.application.impl.StandardLoanFlowProcess;
import pl.com.bottega.lms.model.BookId;
import pl.com.bottega.lms.model.BookRepository;
import pl.com.bottega.lms.model.ClientId;
import pl.com.bottega.lms.model.numbers.BookIdGenerator;
import pl.com.bottega.lms.model.numbers.ISBNIdGenerator;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public LoanFlowProcess loanFlowProcess(BookIdGenerator bookIdGenerator, ClientId clientId) {
        return new StandardLoanFlowProcess(bookIdGenerator, clientId);
    }

    @Bean
    public BookIdGenerator bookIdGenerator() {
        return new ISBNIdGenerator();
    }

    @Bean
    public BookCatalog bookCatalog() {
        return new JPQLBookCatalog();
    }

    @Bean
    public BookRepository bookRepository() {
        return new JPQLBookRepository();
    }

    @Bean
    public ClientId clientId(){
        return new ClientId(1L);
    }

}
