package pl.com.bottega.lms.application.dtos;

import pl.com.bottega.lms.model.BookId;
import pl.com.bottega.lms.model.ClientId;

import java.time.LocalDateTime;

public class LoanDto {

    private Long id;

    private BookId bookId;

    private ClientId clientId;

    private LocalDateTime loanAt;

    private LocalDateTime returnAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookId getBookId() {
        return bookId;
    }

    public void setBookId(BookId bookId) {
        this.bookId = bookId;
    }

    public ClientId getClientId() {
        return clientId;
    }

    public void setClientId(ClientId clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getLoanAt() {
        return loanAt;
    }

    public void setLoanAt(LocalDateTime loanAt) {
        this.loanAt = loanAt;
    }

    public LocalDateTime getReturnAt() {
        return returnAt;
    }

    public void setReturnAt(LocalDateTime returnAt) {
        this.returnAt = returnAt;
    }

}
