package pl.com.bottega.lms.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.lms.application.BookQuery;
import pl.com.bottega.lms.application.BookSearchResults;
import pl.com.bottega.lms.infrastructure.JPABookCatalog;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JPABookCatalogTest {

    @Autowired
    private JPABookCatalog bookCatalog;

    @Test
    @Sql("/fixtures/bookById.sql")
    @Transactional
    public void shouldFindBookByPhase(){
        BookQuery bookQuery = new BookQuery();
        bookQuery.setPhrase("autor");
        BookSearchResults searchResults = bookCatalog.find(bookQuery);

        assertThat(searchResults.getBooks().size()).isEqualTo(5);
        assertThat(searchResults.getBooks().get(0).getId()).isEqualTo("1");
    }

    @Test
    @Sql("/fixtures/bookById.sql")
    @Transactional
    public void shouldFindBookByTitle(){
        BookQuery bookQuery = new BookQuery();
        bookQuery.setTitle("tytul1");
        BookSearchResults searchResults = bookCatalog.find(bookQuery);

        assertThat(searchResults.getBooks().size()).isEqualTo(1);
        assertThat(searchResults.getBooks().get(0).getId()).isEqualTo("1");
    }

    @Test
    @Sql("/fixtures/bookById.sql")
    @Transactional
    public void shouldFindBookByAuthor(){
        BookQuery bookQuery = new BookQuery();
        bookQuery.setAuthor("autor1");
        BookSearchResults searchResults = bookCatalog.find(bookQuery);

        assertThat(searchResults.getBooks().size()).isEqualTo(1);
        assertThat(searchResults.getBooks().get(0).getId()).isEqualTo("1");
    }

    @Test
    @Sql("/fixtures/bookById.sql")
    @Transactional
    public void shouldFindBookByYear(){
        BookQuery bookQuery = new BookQuery();
        bookQuery.setYear("2016");
        BookSearchResults searchResults = bookCatalog.find(bookQuery);

        assertThat(searchResults.getBooks().size()).isEqualTo(1);
        assertThat(searchResults.getBooks().get(0).getId()).isEqualTo("111");
    }

}
