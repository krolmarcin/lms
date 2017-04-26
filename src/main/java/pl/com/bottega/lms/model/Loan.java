package pl.com.bottega.lms.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LOANS")
public class Loan {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookId")
    private Book book;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "clientId"))
    private ClientId clientId;
    private LocalDateTime loanAt;
    private LocalDateTime returnAt;
    private boolean active;

    Loan() {
    }

    public Loan(Book book, ClientId clientId) {
        this.book = book;
        this.clientId = clientId;
        this.loanAt = LocalDateTime.now();
        this.active = true;
    }

    public void returnBook(Book book, ClientId clientId) {
        this.book = book;
        this.clientId = clientId;
        this.returnAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public ClientId getClientId() {
        return clientId;
    }

    public LocalDateTime getLoanAt() {
        return loanAt;
    }

    public LocalDateTime getReturnAt() {
        return returnAt;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
