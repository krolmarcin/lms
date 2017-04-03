package pl.com.bottega.lms.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.lms.application.BookCatalog;
import pl.com.bottega.lms.application.BookManagement;
import pl.com.bottega.lms.model.BookId;
import pl.com.bottega.lms.model.commands.CreateBookCommand;
import pl.com.bottega.lms.model.commands.UpdateBookCommand;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookManagement bookManagement;
    private BookCatalog bookCatalog;

    public BookController(BookManagement bookManagement, BookCatalog bookCatalog) {
        this.bookManagement = bookManagement;
        this.bookCatalog = bookCatalog;
    }

    @PostMapping
    public BookId create(@RequestBody CreateBookCommand cmd) {
        return bookManagement.create(cmd);
    }

    @PutMapping("/{bookId}")
    public void update(@PathVariable String bookId, @RequestBody UpdateBookCommand cmd) {
        cmd.setId(bookId);
        bookManagement.update(cmd);
    }

    @DeleteMapping("/{bookId}")
    public void remove(@PathVariable BookId bookId){
        bookManagement.remove(bookId);
    }

}
