package mk.ukim.finki.emt.lab.exceptions;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(Long id) {
        super("Book " + id + " is not available");
    }
}