package pl.com.bottega.lms.model;

import pl.com.bottega.lms.model.commands.CreateClientCommand;
import pl.com.bottega.lms.model.commands.UpdateClientCommand;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CLIENTS")
public class Client {

    @EmbeddedId
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
