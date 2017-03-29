package pl.com.bottega.lms.model;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(ClientId clientId) {
        super(String.format("Client with ID %s does not exists", clientId.getId()));
    }

}
