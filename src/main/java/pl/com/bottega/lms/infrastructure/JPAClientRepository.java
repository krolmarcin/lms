package pl.com.bottega.lms.infrastructure;

import pl.com.bottega.lms.model.Client;
import pl.com.bottega.lms.model.ClientId;
import pl.com.bottega.lms.model.ClientNotFoundException;
import pl.com.bottega.lms.model.ClientRepository;
import pl.com.bottega.lms.model.commands.CreateClientCommand;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JPAClientRepository implements ClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Client client) {
        entityManager.persist(client);
    }

    @Override
    public void remove(Client client) {
        entityManager.remove(client);
    }

    @Override
    public Client get(ClientId clientId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = builder.createQuery(Client.class);
        Root<Client> root = criteriaQuery.from(Client.class);
        criteriaQuery.where(builder.equal(root.get("clientId"), clientId));
        TypedQuery<Client> query = entityManager.createQuery(criteriaQuery);
        return queryClient(query);
    }

    private Client queryClient(TypedQuery<Client> query) {
        List<Client> clients = query.getResultList();
        if (clients.size() == 0)
            return null;
        else
            return clients.get(0);
    }

}
