package pl.com.bottega.lms.model;

import pl.com.bottega.lms.model.commands.CreateClientCommand;
import pl.com.bottega.lms.model.commands.UpdateClientCommand;

public class Client {

    private ClientId clientId;
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private String email;
    private Address address;

    Client() {
    }

    public Client(CreateClientCommand cmd, ClientId clientId) {
        this.clientId = clientId;
        this.firstName = cmd.getFirstName();
        this.lastName = cmd.getLastName();
        this.mobilePhone = cmd.getMobilePhone();
        this.email = cmd.getEmail();
        this.address = new Address();
    }

    public void update(UpdateClientCommand cmd) {

    }

    public void remove(ClientId clientId) {

    }

}
