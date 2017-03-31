package pl.com.bottega.lms.model;

import pl.com.bottega.lms.model.commands.CreateClientCommand;
import pl.com.bottega.lms.model.commands.UpdateClientCommand;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

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
        this.firstName = cmd.getFirstName();
        this.lastName = cmd.getLastName();
        this.email = cmd.getEmail();
        this.mobilePhone = cmd.getMobilePhone();
    }

    public ClientId getClientId() {
        return clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

}
