package mk.ukim.finki.emt.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.lab.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.models.domain.Author;
import mk.ukim.finki.emt.lab.models.domain.Book;
import mk.ukim.finki.emt.lab.models.domain.User;
import mk.ukim.finki.emt.lab.models.enumerations.Category;
import mk.ukim.finki.emt.lab.models.domain.Country;
import mk.ukim.finki.emt.lab.models.enumerations.Role;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.repository.UserRepository;
import mk.ukim.finki.emt.lab.service.domain.BookService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final BookService bookService;
    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public DataInitializer(BookService bookService, CountryRepository countryRepository, AuthorRepository authorRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.bookService = bookService;
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() throws AuthorNotFoundException {
        countryRepository.save(new Country("Macedonia", "Europe"));
        countryRepository.save(new Country("Niger", "Africa"));

        authorRepository.save(new Author("Jack", "London", countryRepository.findAll().stream().findFirst().get()));

        bookService.save(new Book("Whispers of the Forest", Category.FANTASY, authorRepository.findAll().stream().findFirst().get(), 15));
        bookService.save(new Book("The Quantum Enigma", Category.BIOGRAPHY, authorRepository.findAll().stream().findFirst().get(), 10));
        bookService.save(new Book("The Art of Simplicity", Category.DRAMA, authorRepository.findAll().stream().findFirst().get(), 20));
        bookService.save(new Book("The Hobbit", Category.FANTASY, authorRepository.findAll().stream().findFirst().get(), 0));

        userRepository.save(new User("vasilija", passwordEncoder.encode("vasilija"), "Vase", "Lazarev", Role.ROLE_ADMIN));
        userRepository.save(new User("damjan", passwordEncoder.encode("damjan"), "Damjan", "Sandev", Role.ROLE_USER));
        userRepository.save(new User("branko", passwordEncoder.encode("branko"), "Branko", "Milenkov", Role.ROLE_LIBRARIAN));

    }
}
