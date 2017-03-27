package pl.com.bottega.lms.model;

import java.time.LocalDateTime;

public class Loan {

    private Long id;
    private BookId bookId;
    private ClientId clientId;
    private LocalDateTime loanAt;
    private LocalDateTime returnAt;

    Loan() {
    }

    public Loan(BookId bookId, ClientId clientId) {
        this.bookId = bookId;
        this.clientId = clientId;
        this.loanAt = LocalDateTime.now();
    }

    public void returnBook() {

    }

}
