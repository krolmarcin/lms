package pl.com.bottega.lms.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.bottega.lms.model.commands.CreateBookCommand;
import pl.com.bottega.lms.model.commands.UpdateBookCommand;
import pl.com.bottega.lms.model.numbers.BookIdGenerator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookTest {

    @Test
    public void shouldSetAuthorTitleAndYearOnCreate() {
        //given
        Book book = given().newBook();

        //then
        assertEquals("Author", book.getAuthor());
        assertEquals("Title", book.getTitle());
        assertEquals("1990", book.getYear());
    }

    @Test
    public void shouldGenerateIdOnCreate() {
        //given
        Book book = given().newBook();

        //then
        assertEquals(anyBookId(), book.getId());
    }

    @Test
    public void shouldUpdateAuthorTitleAndYear() {
        //given
        Book book = given().newBook();

        //when
        UpdateBookCommand cmd = new UpdateBookCommand();
        cmd.setAuthor("changed author");
        cmd.setTitle("changed title");
        cmd.setYear("changed 1800");
        book.update(cmd);

        //then
        assertEquals("changed author", book.getAuthor());
        assertEquals("changed title", book.getTitle());
        assertEquals("changed 1800", book.getYear());
    }

    public BookAssembler given() {
        return new BookAssembler();
    }

    public BookId anyBookId() {
        return new BookId("1");
    }

    public class BookAssembler {

        public Book newBook() {
            CreateBookCommand cmd = new CreateBookCommand();
            cmd.setAuthor("Author");
            cmd.setTitle("Title");
            cmd.setYear("1990");
            BookIdGenerator bookIdGenerator = mock(BookIdGenerator.class);
            when(bookIdGenerator.generate()).thenReturn(anyBookId());
            return new Book(cmd, bookIdGenerator);
        }

    }

}
