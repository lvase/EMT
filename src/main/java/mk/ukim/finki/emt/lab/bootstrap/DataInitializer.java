package mk.ukim.finki.emt.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.lab.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.models.Author;
import mk.ukim.finki.emt.lab.models.Category;
import mk.ukim.finki.emt.lab.models.Country;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.service.AuthorService;
import mk.ukim.finki.emt.lab.service.BookService;
import mk.ukim.finki.emt.lab.service.CountryService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final BookService bookService;
    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;

    public DataInitializer(BookService bookService, CountryRepository countryRepository, AuthorRepository authorRepository) {
        this.bookService = bookService;
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
    }

    @PostConstruct
    public void init() throws AuthorNotFoundException {
        countryRepository.save(new Country("Macedonia", "Europe"));
        countryRepository.save(new Country("Niger", "Africa"));
        authorRepository.save(new Author("Jack", "London", countryRepository.findAll().stream().findFirst().get()));
        bookService.addNewBook("Whispers of the Forest", Category.FANTASY, authorRepository.findAll().stream().findFirst().get().getId(), 15);
        bookService.addNewBook("The Quantum Enigma", Category.BIOGRAPHY, authorRepository.findAll().stream().findFirst().get().getId(), 10);
        bookService.addNewBook("The Art of Simplicity", Category.DRAMA, authorRepository.findAll().stream().findFirst().get().getId(), 20);

    }
}
