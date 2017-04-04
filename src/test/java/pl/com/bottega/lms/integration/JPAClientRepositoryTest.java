package pl.com.bottega.lms.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.lms.infrastructure.JPAClientRepository;
import pl.com.bottega.lms.model.Client;
import pl.com.bottega.lms.model.ClientId;
import pl.com.bottega.lms.model.commands.CreateClientCommand;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class JPAClientRepositoryTest {

    @Autowired
    private JPAClientRepository clientRepository;

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    public void shouldFindClientById() {
        //given - sql
        //when
        CreateClientCommand cmd = new CreateClientCommand();
        cmd.setClientId(new ClientId(1L));
        Client client = clientRepository.get(cmd.getClientId());

        //then
        assertThat(client).isNotNull();
        assertThat(client.getClientId()).isEqualTo(new ClientId(1L));
    }

    @Test
    @Sql("/fixtures/clientsAndBooks.sql")
    public void shouldNotFindClientByIdWhenIdNotExists() {
        CreateClientCommand cmd = new CreateClientCommand();
        ClientId clientId = new ClientId(1111L);
        cmd.setClientId(clientId);
        Client client = clientRepository.get(cmd.getClientId());

        assertThat(client).isNull();
    }

}
