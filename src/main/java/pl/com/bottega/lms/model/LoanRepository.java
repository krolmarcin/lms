package pl.com.bottega.lms.model;

public interface LoanRepository {

    void put(Loan loan);

    Loan getForClientId(ClientId clientId);

    Loan getForBookId(BookId bookId);

}
