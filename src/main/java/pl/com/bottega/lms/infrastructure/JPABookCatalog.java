package pl.com.bottega.lms.infrastructure;

import pl.com.bottega.lms.application.BookCatalog;
import pl.com.bottega.lms.application.BookDto;
import pl.com.bottega.lms.application.BookQuery;
import pl.com.bottega.lms.application.BookSearchResults;
import pl.com.bottega.lms.model.Book;
import pl.com.bottega.lms.model.BookId;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class JPABookCatalog implements BookCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BookSearchResults find(BookQuery bookQuery) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        root.fetch("loans", JoinType.LEFT);
        Set<Predicate> predicates = createPredicates(bookQuery, criteriaBuilder, root);
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        Query query = entityManager.createQuery(criteriaQuery);
        List<Book> books = query.getResultList();
        BookSearchResults results = new BookSearchResults();
        List<BookDto> bookDtos = new LinkedList<>();
        for (Book book : books) {
            bookDtos.add(createBookDto(book));
        }
        results.setBooks(bookDtos);
        return results;
    }


    private Set<Predicate> createPredicates(BookQuery bookQuery, CriteriaBuilder criteriaBuilder, Root<Book> root) {
        Set<Predicate> predicates = new HashSet<>();
        addPhrasePredicate(bookQuery, criteriaBuilder, root, predicates);
        addAuthorPredicate(bookQuery, criteriaBuilder, root, predicates);
        addTitlePredicate(bookQuery, criteriaBuilder, root, predicates);
        addYearPredicate(bookQuery, criteriaBuilder, root, predicates);
        return predicates;
    }

    private void addYearPredicate(BookQuery bookQuery, CriteriaBuilder criteriaBuilder, Root<Book> root, Set<Predicate> predicates) {
        if (bookQuery.getYear() != null) {
            predicates.add(criteriaBuilder.equal(root.get("year"), bookQuery.getYear()));
        }
    }

    private void addTitlePredicate(BookQuery bookQuery, CriteriaBuilder criteriaBuilder, Root<Book> root, Set<Predicate> predicates) {
        if (bookQuery.getTitle() != null) {
            predicates.add(criteriaBuilder.equal(root.get("title"), bookQuery.getTitle()));
        }
    }

    private void addAuthorPredicate(BookQuery bookQuery, CriteriaBuilder criteriaBuilder, Root<Book> root, Set<Predicate> predicates) {
        if (bookQuery.getAuthor() != null) {
            predicates.add(criteriaBuilder.equal(root.get("author"), bookQuery.getAuthor()));
        }
    }

    private void addPhrasePredicate(BookQuery bookQuery, CriteriaBuilder criteriaBuilder, Root<Book> root, Set<Predicate> predicates) {
        if (bookQuery.getPhrase() != null) {
            String likeExpression = "%" + bookQuery.getPhrase() + "%";
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(root.get("title"), likeExpression),
                    criteriaBuilder.like(root.get("author"), likeExpression),
                    criteriaBuilder.like(root.get("year"), likeExpression)
                    )
            );
        }
    }

    @Override
    public BookDto get(BookId bookId) {
        Book book = entityManager.find(Book.class, bookId);
        BookDto bookDto = createBookDto(book);
        return bookDto;
    }

    private BookDto createBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId().getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setYear(book.getYear());
        return bookDto;
    }

}
