package pl.com.bottega.lms.model;

public interface ClientRepository {

    Client findByClientId(ClientId clientId);

    void put(Client client);

}
