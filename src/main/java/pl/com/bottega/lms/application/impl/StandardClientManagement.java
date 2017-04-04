package pl.com.bottega.lms.application.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.lms.application.ClientManagement;
import pl.com.bottega.lms.model.Client;
import pl.com.bottega.lms.model.ClientId;
import pl.com.bottega.lms.model.ClientRepository;
import pl.com.bottega.lms.model.commands.CreateClientCommand;
import pl.com.bottega.lms.model.commands.UpdateClientCommand;

@Transactional
public class StandardClientManagement implements ClientManagement {

    private ClientRepository clientRepository;

    public StandardClientManagement(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientId create(CreateClientCommand cmd) {
        Client client = new Client(cmd);
        clientRepository.put(client);
        return cmd.getClientId();
    }

    @Override
    public void update(UpdateClientCommand cmd) {
        Client client = clientRepository.get(cmd.getClientId());
        client.update(cmd);

    }

    @Override
    public void remove(ClientId clientId) {
        Client client = clientRepository.get(clientId);
        clientRepository.remove(client);
    }

}
