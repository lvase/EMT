package mk.ukim.finki.emt.lab.service.impl.impl;

import mk.ukim.finki.emt.lab.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.lab.models.domain.Author;
import mk.ukim.finki.emt.lab.models.domain.Country;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.service.impl.AuthorService;
import mk.ukim.finki.emt.lab.service.impl.CountryServicee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImplementation implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryServicee countryService;

    public AuthorServiceImplementation(AuthorRepository authorRepository, CountryServicee countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public Optional<Author> findById(Long id) throws AuthorNotFoundException {
        return Optional.of(authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id)));
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author addAuthor(String name, String surname, Long countryID) throws CountryNotFoundException {
        Country country = countryService.findById(countryID).get();
        return authorRepository.save(new Author(name, surname, country));
    }

    @Override
    public Author deleteAuthor(Long id) {
        Author deletedAuthor = authorRepository.findById(id).get();
        authorRepository.deleteById(id);
        return deletedAuthor;
    }

    @Override
    public void editAuthor(Long id, String name, String surname, Long countryId) throws CountryNotFoundException, AuthorNotFoundException {
        Author authorToEdit = findById(id).get();
        authorToEdit.setName(name);
        authorToEdit.setSurname(surname);
        Country country = countryService.findById(countryId).get();
        authorToEdit.setCountry(country);
        authorRepository.save(authorToEdit);
    }
}
