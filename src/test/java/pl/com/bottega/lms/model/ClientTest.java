package pl.com.bottega.lms.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.bottega.lms.model.commands.CreateClientCommand;
import pl.com.bottega.lms.model.commands.UpdateClientCommand;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest {

    @Test
    public void shouldSetFirstNameLastNameEmailAndMobilePhoneOnCreate() {
        //given
        Client client = given().newClient();

        //when

        //then
        assertEquals("janek", client.getFirstName());
        assertEquals("kowalski", client.getLastName());
        assertEquals("email@email.com", client.getEmail());
        assertEquals("777888999", client.getMobilePhone());
    }

    @Test
    public void shouldAllowUpdateFirstName() {
        //given
        Client client = given().newClient();

        //when
        UpdateClientCommand cmd = new UpdateClientCommand();
        cmd.setFirstName("czesiek");
        client.update(cmd);

        //then
        assertEquals("czesiek", client.getFirstName());
    }

    @Test
    public void shouldAllowUpdateLastName() {
        Client client = given().newClient();

        UpdateClientCommand cmd = new UpdateClientCommand();
        cmd.setLastName("kowal");
        client.update(cmd);

        assertEquals("kowal", client.getLastName());
    }

    @Test
    public void shouldAllowUpdateEmailAndMobilePhone() {
        Client client = given().newClient();

        UpdateClientCommand cmd = new UpdateClientCommand();
        cmd.setEmail("new email");
        cmd.setMobilePhone("123456");
        client.update(cmd);

        assertEquals("new email", client.getEmail());
        assertEquals("123456", client.getMobilePhone());
    }

    private ClientAssembler given() {
        return new ClientAssembler();
    }

    private ClientId anyClientId() {
        return new ClientId(1L);
    }

    private class ClientAssembler {

        public Client newClient() {
            CreateClientCommand cmd = new CreateClientCommand();
            cmd.setFirstName("janek");
            cmd.setLastName("kowalski");
            cmd.setEmail("email@email.com");
            cmd.setMobilePhone("777888999");
            return new Client(cmd);
        }
    }

}
