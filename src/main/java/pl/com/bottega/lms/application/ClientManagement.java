package pl.com.bottega.lms.application;

import pl.com.bottega.lms.model.Client;
import pl.com.bottega.lms.model.ClientId;
import pl.com.bottega.lms.model.commands.CreateClientCommand;
import pl.com.bottega.lms.model.commands.UpdateClientCommand;

public interface ClientManagement {

    ClientId create(CreateClientCommand cmd);

    void update(UpdateClientCommand cmd);

    void remove(ClientId clientId);

}
