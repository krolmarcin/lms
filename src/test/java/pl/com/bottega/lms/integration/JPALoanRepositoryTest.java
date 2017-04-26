package pl.com.bottega.lms.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.lms.infrastructure.JPABookCatalog;
import pl.com.bottega.lms.infrastructure.JPABookRepository;
import pl.com.bottega.lms.infrastructure.JPAClientCatalog;
import pl.com.bottega.lms.infrastructure.JPAClientRepository;
import pl.com.bottega.lms.model.BookId;
import pl.com.bottega.lms.model.ClientId;
import pl.com.bottega.lms.model.Loan;
import pl.com.bottega.lms.model.LoanRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class JPALoanRepositoryTest {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private JPABookRepository bookRepository;

    @Autowired
    private JPAClientRepository clientRepository;

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    public void shouldFindLoanByBookId() {
        BookId bookId = new BookId("1");

        Loan loan = loanRepository.getForBookId(bookId);

        assertThat(loan.getId()).isEqualTo(1L);
        //assertThat(loan.getBookId()).isEqualTo(new BookId("1"));
        assertThat(loan.getClientId()).isEqualTo(new ClientId(1L));
        assertThat(loan.getLoanAt()).isEqualTo("2017-03-04T07:00:00");
    }

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    public void shouldFindLoanByClientId() {
        ClientId clientId = new ClientId(555L);

        Loan loan = loanRepository.getForClientId(clientId);

        //assertThat(loan.getBookId()).isEqualTo(new BookId("555"));
        assertThat(loan.getReturnAt()).isEqualTo("2017-03-04T10:44:00");
    }

}
