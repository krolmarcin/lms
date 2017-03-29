package pl.com.bottega.lms.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.com.bottega.lms.infrastructure.JPAClientRepository;
import pl.com.bottega.lms.model.Client;
import pl.com.bottega.lms.model.ClientId;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JPAClientRepositoryTest {

    @Autowired
    private JPAClientRepository clientRepository;

    @Test
    @Sql("/fixtures/clientById.sql")
    public void shouldFindClientById() {
        //given - sql
        //when
        ClientId id = new ClientId(1L);
        Client client = clientRepository.findByClientId(id);

        //then
        assertThat(client).isNotNull();
        assertThat(client.getClientId()).isEqualTo(new ClientId(1L));
    }

}
