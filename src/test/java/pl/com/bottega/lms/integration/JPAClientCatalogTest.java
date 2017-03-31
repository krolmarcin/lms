package pl.com.bottega.lms.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.lms.application.BookQuery;
import pl.com.bottega.lms.application.BookSearchResults;
import pl.com.bottega.lms.application.ClientQuery;
import pl.com.bottega.lms.application.ClientSearchResults;
import pl.com.bottega.lms.infrastructure.JPABookCatalog;
import pl.com.bottega.lms.infrastructure.JPAClientCatalog;
import pl.com.bottega.lms.model.Client;
import pl.com.bottega.lms.model.ClientId;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JPAClientCatalogTest {

    @Autowired
    private JPAClientCatalog clientCatalog;

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    @Transactional
    public void shouldFindClientByPhase() {
        ClientQuery clientQuery = new ClientQuery();
        clientQuery.setPhrase("janek");
        ClientSearchResults searchResults = clientCatalog.find(clientQuery);

        assertThat(searchResults.getClients().size()).isEqualTo(2);
        assertThat(searchResults.getClients().get(0).getClientId().getId()).isEqualTo(1L);
        assertThat(searchResults.getClients().get(1).getClientId().getId()).isEqualTo(3L);
    }

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    @Transactional
    public void shouldFindClientByFirstAndLastName() {
        ClientQuery clientQuery = new ClientQuery();
        clientQuery.setFirstName("krzysztof");
        clientQuery.setLastName("ibisz");
        ClientSearchResults searchResults = clientCatalog.find(clientQuery);

        assertThat(searchResults.getClients().size()).isEqualTo(1);
        assertThat(searchResults.getClients().get(0).getClientId().getId()).isEqualTo(555L);
    }

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    @Transactional
    public void shouldFindClientById() {
        ClientQuery clientQuery = new ClientQuery();
        clientQuery.setClientId(new ClientId(555L));
        ClientSearchResults searchResults = clientCatalog.find(clientQuery);

        assertThat(searchResults.getClients().size()).isEqualTo(1);
        assertThat(searchResults.getClients().get(0).getLastName()).isEqualTo("ibisz");
        assertThat(searchResults.getClients().get(0).getClientId().getId()).isEqualTo(555L);
    }


}
