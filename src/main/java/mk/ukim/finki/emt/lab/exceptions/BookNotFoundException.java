package mk.ukim.finki.emt.lab.exceptions;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(Long id) {
        super("The book with id:" +id+" was not found!");
    }
}
