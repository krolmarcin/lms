package pl.com.bottega.lms.infrastructure;

import pl.com.bottega.lms.application.ClientCatalog;
import pl.com.bottega.lms.application.ClientDto;
import pl.com.bottega.lms.application.ClientQuery;
import pl.com.bottega.lms.application.ClientSearchResults;
import pl.com.bottega.lms.model.Client;
import pl.com.bottega.lms.model.ClientId;
import pl.com.bottega.lms.model.ClientNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class JPAClientCatalog implements ClientCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ClientSearchResults find(ClientQuery clientQuery) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> root = criteriaQuery.from(Client.class);
        Set<Predicate> predicates = createPredicates(clientQuery, criteriaBuilder, root);
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        Query query = entityManager.createQuery(criteriaQuery);
        List<Client> clients = query.getResultList();
        ClientSearchResults results = new ClientSearchResults();
        List<ClientDto> clientDtos = new LinkedList<>();
        for (Client client : clients) {
            clientDtos.add(createClientDto(client));
        }
        results.setClients(clientDtos);
        return results;
    }

    private Set<Predicate> createPredicates(ClientQuery clientQuery, CriteriaBuilder criteriaBuilder, Root<Client> root) {
        Set<Predicate> predicates = new HashSet<>();
        addPhrasePredicate(clientQuery, criteriaBuilder, root, predicates);
        addIdPredicate(clientQuery, criteriaBuilder, root, predicates);
        addFirstNamePredicate(clientQuery, criteriaBuilder, root, predicates);
        addLastNamePredicate(clientQuery, criteriaBuilder, root, predicates);
        addEmailPredicate(clientQuery, criteriaBuilder, root, predicates);
        addMobilePhonePredicate(clientQuery, criteriaBuilder, root, predicates);
        return predicates;
    }

    private void addFirstNamePredicate(ClientQuery clientQuery, CriteriaBuilder criteriaBuilder, Root<Client> root, Set<Predicate> predicates) {
        if (clientQuery.getFirstName() != null) {
            predicates.add(criteriaBuilder.equal(root.get("firstName"), clientQuery.getFirstName()));
        }
    }

    private void addLastNamePredicate(ClientQuery clientQuery, CriteriaBuilder criteriaBuilder, Root<Client> root, Set<Predicate> predicates) {
        if (clientQuery.getLastName() != null) {
            predicates.add(criteriaBuilder.equal(root.get("lastName"), clientQuery.getLastName()));
        }
    }

    private void addMobilePhonePredicate(ClientQuery clientQuery, CriteriaBuilder criteriaBuilder, Root<Client> root, Set<Predicate> predicates) {
        if (clientQuery.getMobilePhone() != null) {
            predicates.add(criteriaBuilder.equal(root.get("mobilePhone"), clientQuery.getMobilePhone()));
        }
    }

    private void addEmailPredicate(ClientQuery clientQuery, CriteriaBuilder criteriaBuilder, Root<Client> root, Set<Predicate> predicates) {
        if (clientQuery.getEmail() != null) {
            predicates.add(criteriaBuilder.equal(root.get("email"), clientQuery.getEmail()));
        }
    }

    private void addIdPredicate(ClientQuery clientQuery, CriteriaBuilder criteriaBuilder, Root<Client> root, Set<Predicate> predicates) {
        if (clientQuery.getClientId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("clientId"), clientQuery.getClientId()));
        }
    }

    private void addPhrasePredicate(ClientQuery clientQuery, CriteriaBuilder criteriaBuilder, Root<Client> root, Set<Predicate> predicates) {
        if (clientQuery.getPhrase() != null) {
            String likeExpression = "%" + clientQuery.getPhrase() + "%";
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(root.get("firstName"), likeExpression),
                    criteriaBuilder.like(root.get("lastName"), likeExpression),
                    criteriaBuilder.like(root.get("email"), likeExpression),
                    criteriaBuilder.like(root.get("mobilePhone"), likeExpression)
                    )
            );
        }
    }

    @Override
    public ClientDto get(ClientId clientId) {
        Query query = entityManager.createQuery("FROM Client d WHERE d.id = :id");
        query.setParameter("id", clientId);
        if (query.getResultList().isEmpty())
            throw new ClientNotFoundException(clientId);
        else {
            Client client = (Client) query.getResultList().get(0);
            ClientDto clientDto = createClientDto(client);
            return clientDto;
        }
    }

    private ClientDto createClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(client.getClientId());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setEmail(client.getEmail());
        clientDto.setMobilePhone(client.getMobilePhone());
        return clientDto;
    }

}
