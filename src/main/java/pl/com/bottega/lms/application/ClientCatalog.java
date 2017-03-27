package pl.com.bottega.lms.application;

import pl.com.bottega.lms.model.ClientId;

public interface ClientCatalog {

    ClientSearchResult find(ClientQuery clientQuery);

    ClientDto get(ClientId clientId);

}
