package pl.com.bottega.lms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.lms.application.BookCatalog;
import pl.com.bottega.lms.application.ClientCatalog;
import pl.com.bottega.lms.application.LoanFlowProcess;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class LoanFlowProcessTest {

    @Autowired
    private LoanFlowProcess loanFlowProcess;

    @Autowired
    private BookCatalog bookCatalog;

    @Autowired
    private ClientCatalog clientCatalog;

    @Test
    public void shouldCreateLoan(){

    }

}
