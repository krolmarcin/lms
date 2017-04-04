package pl.com.bottega.lms.model;

public class LoanNotFoundException extends RuntimeException {

    public LoanNotFoundException(String msg) {
        super(msg);
    }

}
