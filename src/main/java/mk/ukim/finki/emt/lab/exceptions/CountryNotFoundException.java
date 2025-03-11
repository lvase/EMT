package mk.ukim.finki.emt.lab.exceptions;

public class CountryNotFoundException extends Exception{
    public CountryNotFoundException(Long id) {
        super("The country with id: " + id + " was not found!");
    }
}
