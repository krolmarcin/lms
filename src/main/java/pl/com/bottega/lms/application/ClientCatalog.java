package pl.com.bottega.lms.application;

import pl.com.bottega.lms.application.dtos.ClientDto;
import pl.com.bottega.lms.model.ClientId;

public interface ClientCatalog {

    ClientSearchResults find(ClientQuery clientQuery);

    ClientDto get(ClientId clientId);

}
