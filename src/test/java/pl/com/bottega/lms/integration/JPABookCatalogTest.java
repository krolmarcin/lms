package pl.com.bottega.lms.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.lms.application.dtos.BookDto;
import pl.com.bottega.lms.application.BookQuery;
import pl.com.bottega.lms.application.BookSearchResults;
import pl.com.bottega.lms.infrastructure.JPABookCatalog;
import pl.com.bottega.lms.model.BookId;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JPABookCatalogTest {

    @Autowired
    private JPABookCatalog bookCatalog;

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    @Transactional
    public void shouldFindBookByPhase() {
        BookQuery bookQuery = new BookQuery();
        bookQuery.setPhrase("autor");
        BookSearchResults searchResults = bookCatalog.find(bookQuery);

        assertThat(searchResults.getBooks().size()).isEqualTo(5);
        assertThat(searchResults.getBooks().get(0).getId()).isEqualTo("1");
    }

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    @Transactional
    public void shouldFindBookByTitle() {
        BookQuery bookQuery = new BookQuery();
        bookQuery.setTitle("tytul1");
        BookSearchResults searchResults = bookCatalog.find(bookQuery);

        assertThat(searchResults.getBooks().size()).isEqualTo(1);
        assertThat(searchResults.getBooks().get(0).getId()).isEqualTo("1");
    }

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    @Transactional
    public void shouldFindBookByAuthor() {
        BookQuery bookQuery = new BookQuery();
        bookQuery.setAuthor("autor1");
        BookSearchResults searchResults = bookCatalog.find(bookQuery);

        assertThat(searchResults.getBooks().size()).isEqualTo(1);
        assertThat(searchResults.getBooks().get(0).getId()).isEqualTo("1");
    }

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    @Transactional
    public void shouldFindBookByYear() {
        BookQuery bookQuery = new BookQuery();
        bookQuery.setYear("2017");
        BookSearchResults searchResults = bookCatalog.find(bookQuery);

        assertThat(searchResults.getBooks().size()).isEqualTo(4);
        assertThat(searchResults.getBooks().get(0).getId()).isEqualTo("1");
        assertThat(searchResults.getBooks().get(1).getId()).isEqualTo("2");
        assertThat(searchResults.getBooks().get(2).getId()).isEqualTo("3");
        assertThat(searchResults.getBooks().get(3).getId()).isEqualTo("555");
    }

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    @Transactional
    public void shouldGetBookById() {
        BookId bookId = new BookId("1");
        BookDto bookDto = bookCatalog.get(bookId);

        assertThat(bookDto.getId()).isEqualTo("1");
    }


}
