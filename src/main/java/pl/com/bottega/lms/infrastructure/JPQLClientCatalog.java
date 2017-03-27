package pl.com.bottega.lms.infrastructure;

import pl.com.bottega.lms.application.ClientCatalog;
import pl.com.bottega.lms.application.ClientDto;
import pl.com.bottega.lms.application.ClientQuery;
import pl.com.bottega.lms.application.ClientSearchResult;
import pl.com.bottega.lms.model.ClientId;

public class JPQLClientCatalog implements ClientCatalog {

    @Override
    public ClientSearchResult find(ClientQuery clientQuery) {
        return null;
    }

    @Override
    public ClientDto get(ClientId clientId) {
        return null;
    }

}
