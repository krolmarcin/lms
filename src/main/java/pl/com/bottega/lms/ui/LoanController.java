package pl.com.bottega.lms.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.lms.application.LoanFlowProcess;
import pl.com.bottega.lms.model.BookId;
import pl.com.bottega.lms.model.ClientId;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private LoanFlowProcess loanFlowProcess;

    public LoanController(LoanFlowProcess loanFlowProcess) {
        this.loanFlowProcess = loanFlowProcess;
    }

    @PostMapping("/loan/{clientIdIn}/{bookIdIn}")
    public void loan(@PathVariable String clientIdIn, @PathVariable String bookIdIn) {
        ClientId clientId = new ClientId(Long.parseLong(clientIdIn));
        BookId bookId = new BookId(bookIdIn);
        loanFlowProcess.loanBook(bookId, clientId);
    }

    @PostMapping("/return/{clientIdIn}/{bookIdIn}")
    public void returnBook(@PathVariable String clientIdIn, @PathVariable String bookIdIn) {
        BookId bookId = new BookId(bookIdIn);
        ClientId clientId = new ClientId(Long.parseLong(clientIdIn));
        loanFlowProcess.returnBook(bookId, clientId);
    }

}
