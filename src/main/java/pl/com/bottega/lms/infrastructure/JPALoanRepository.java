package pl.com.bottega.lms.infrastructure;

import pl.com.bottega.lms.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            throw new LoanNotFoundException(String.format("No loans for this query"));
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

    @Override
    public Loan getActiveForBookIdAndClientId(BookId bookId, ClientId clientId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Loan> criteriaQuery = builder.createQuery(Loan.class);
        Root<Loan> root = criteriaQuery.from(Loan.class);
        Set<Predicate> predicates = new HashSet<>();
        predicates.add(builder.equal(root.get("bookId"), bookId));
        predicates.add(builder.equal(root.get("clientId"), clientId));
        predicates.add(builder.equal(root.get("active"), true));
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Loan> query = entityManager.createQuery(criteriaQuery);
        return loanQuery(query);
    }


}
