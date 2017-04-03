package pl.com.bottega.lms.infrastructure;

import pl.com.bottega.lms.model.BookId;
import pl.com.bottega.lms.model.ClientId;
import pl.com.bottega.lms.model.Loan;
import pl.com.bottega.lms.model.LoanRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JPALoanRepository implements LoanRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Loan loan) {
        entityManager.persist(loan);
    }

    @Override
    public Loan getForClientId(ClientId clientId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Loan> criteriaQuery = criteriaBuilder.createQuery(Loan.class);
        Root<Loan> root = criteriaQuery.from(Loan.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("clientId"), clientId));
        TypedQuery<Loan> query = entityManager.createQuery(criteriaQuery);
        return loanQuery(query);
    }

    private Loan loanQuery(TypedQuery<Loan> query) {
        List<Loan> loans = query.getResultList();
        if (loans.size() == 0)
            return null;
        else
            return loans.get(0);
    }

    @Override
    public Loan getForBookId(BookId bookId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Loan> criteriaQuery = builder.createQuery(Loan.class);
        Root<Loan> root = criteriaQuery.from(Loan.class);
        criteriaQuery.where(builder.equal(root.get("bookId"), bookId));
        TypedQuery<Loan> query = entityManager.createQuery(criteriaQuery);
        return loanQuery(query);
    }

}
