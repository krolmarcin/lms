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

    @PostMapping("/loan/{clientId_string}/{bookId_string}")
    public void loan(@PathVariable String clientId_string, @PathVariable String bookId_string) {
        ClientId clientId = new ClientId(Long.parseLong(clientId_string));
        BookId bookId = new BookId(bookId_string);
        loanFlowProcess.loanBook(bookId, clientId);
    }

    @PostMapping("/return/{clientId_string}/{bookId_string}")
    public void returnBook(@PathVariable String clientId_string, @PathVariable String bookId_string) {
        BookId bookId = new BookId(bookId_string);
        ClientId clientId = new ClientId(Long.parseLong(clientId_string));
        loanFlowProcess.returnBook(bookId, clientId);
    }

}
