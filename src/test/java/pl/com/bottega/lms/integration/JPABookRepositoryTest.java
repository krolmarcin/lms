package pl.com.bottega.lms.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.lms.infrastructure.JPABookRepository;
import pl.com.bottega.lms.model.Book;
import pl.com.bottega.lms.model.BookId;
import pl.com.bottega.lms.model.BookNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class JPABookRepositoryTest {

    @Autowired
    private JPABookRepository bookRepository;

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    public void shouldFindBookById() {
        //given - sql
        //when
        BookId bookId = new BookId("1");
        Book book = bookRepository.get(bookId);

        //then
        assertThat(book.getId()).isEqualTo(new BookId("1"));
    }

    @Test(expected = BookNotFoundException.class)
    @Sql("/fixtures/clientsAndBooks.sql")
    public void shouldNotFindBookByNonExistsId() {
        //given - sql
        //when
        BookId id = new BookId("123");
        Book book = bookRepository.get(id);

        //then - exception
    }

    @Test(expected = BookNotFoundException.class)
    @Sql("/fixtures/clientsAndBooks.sql")
    public void shouldRemoveBookById() {
        BookId id = new BookId("1");
        Book book = bookRepository.get(id);

        bookRepository.remove(book);
        bookRepository.get(id);
    }

}
