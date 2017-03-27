package pl.com.bottega.lms.model;

public interface ClientRepository {

    void put(Client client);

    Client get(ClientId clientId);

}
