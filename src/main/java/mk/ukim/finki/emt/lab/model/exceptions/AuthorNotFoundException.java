package mk.ukim.finki.emt.lab.model.exceptions;

public class AuthorNotFoundException extends Exception{

    public AuthorNotFoundException(Long id) {
        super("The author with id: "+id+" was not found!");
    }
}
