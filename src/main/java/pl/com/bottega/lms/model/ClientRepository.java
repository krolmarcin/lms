package pl.com.bottega.lms.model;

public interface ClientRepository {

    Client get(ClientId clientId);

    void put(Client client);

    void remove(ClientId clientId);

}
