package pl.com.bottega.lms.infrastructure;

import pl.com.bottega.lms.model.Client;
import pl.com.bottega.lms.model.ClientId;
import pl.com.bottega.lms.model.ClientRepository;

public class JPQLClientRepository implements ClientRepository {

    @Override
    public void put(Client client) {

    }

    @Override
    public void remove(ClientId clientId) {

    }

    @Override
    public Client get(ClientId clientId) {
        return null;
    }

}
