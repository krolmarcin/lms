package pl.com.bottega.lms.ui;


import org.springframework.web.bind.annotation.*;
import pl.com.bottega.lms.application.ClientCatalog;
import pl.com.bottega.lms.application.ClientManagement;
import pl.com.bottega.lms.model.ClientId;
import pl.com.bottega.lms.model.commands.CreateClientCommand;
import pl.com.bottega.lms.model.commands.UpdateClientCommand;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientManagement clientManagement;
    private ClientCatalog clientCatalog;

    public ClientController(ClientManagement clientManagement, ClientCatalog clientCatalog) {
        this.clientManagement = clientManagement;
        this.clientCatalog = clientCatalog;
    }

    @PostMapping
    public ClientId create(@RequestBody CreateClientCommand cmd) {
        return clientManagement.create(cmd);
    }

    @PutMapping("/{clientId}")
    public void update(@PathVariable ClientId clientId, @RequestBody UpdateClientCommand cmd) {
        cmd.setClientId(clientId);
        clientManagement.update(cmd);
    }

    @DeleteMapping("/{clientId_string}")
    public void remove(@PathVariable String clientId_string) {
        ClientId clientId = new ClientId(Long.parseLong(clientId_string));
        clientManagement.remove(clientId);
    }

}
