package pl.com.bottega.lms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.lms.application.BookCatalog;
import pl.com.bottega.lms.application.ClientCatalog;
import pl.com.bottega.lms.application.LoanFlowProcess;
import pl.com.bottega.lms.model.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class LoanFlowProcessTest {

    @Autowired
    private LoanFlowProcess loanFlowProcess;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BookRepository bookRepository;

    private BookTest bookTest;

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    public void shouldCreateLoan() {
        BookId bookId = new BookId("1");
        ClientId clientId = new ClientId(1L);

        loanFlowProcess.loanBook(bookId, clientId);
    }

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    public void shouldNotBeAvailableAfterLoan(){
        BookId bookId = new BookId("1");
        ClientId clientId = new ClientId(1L);

        loanFlowProcess.loanBook(bookId, clientId);
        Book book = bookRepository.get(bookId);

        assertThat(book.isAvailable()).isEqualTo(false);
    }

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    public void shouldBeAvailableAfterReturn(){
        BookId bookId = new BookId("1");
        ClientId clientId = new ClientId(1L);

        loanFlowProcess.loanBook(bookId, clientId);
        Book book = bookRepository.get(bookId);

        loanFlowProcess.returnBook(bookId);
        assertThat(book.isAvailable()).isEqualTo(true);
    }

}
