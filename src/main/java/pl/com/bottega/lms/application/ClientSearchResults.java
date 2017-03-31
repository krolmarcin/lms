package pl.com.bottega.lms.application;

import java.util.List;

public class ClientSearchResults {

    private List<ClientDto> clients;

    public void setClients(List<ClientDto> clients) {
        this.clients = clients;
    }

    public List<ClientDto> getClients() {
        return clients;
    }

}
