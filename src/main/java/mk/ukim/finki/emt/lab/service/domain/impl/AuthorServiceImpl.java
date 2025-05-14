package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.events.AuthorChangedEvent;
import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.model.projections.AuthorProjection;
import mk.ukim.finki.emt.lab.model.views.AuthorsPerCountryView;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.repository.AuthorsPerCountryRepository;
import mk.ukim.finki.emt.lab.service.domain.AuthorService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorsPerCountryRepository authorsPerCountryViewRepository;
    private final CountryService countryService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorsPerCountryRepository authorsPerCountryViewRepository, CountryService countryService, ApplicationEventPublisher eventPublisher) {
        this.authorRepository = authorRepository;
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
        this.countryService = countryService;
        this.applicationEventPublisher = eventPublisher;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> withMostRentedBooks() {
        Map<Author, Integer> map = new HashMap<>();
        this.getAllAuthors().forEach(author -> {
            int sum = author.getBooks().stream().mapToInt(Book::getTimesRented).sum();
            map.put(author, sum);
        });
        return Optional.of(map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey());
    }

    @Override
    public Optional<Author> save(Author author) {
        Optional<Author> savedAuthor = Optional.empty();
        if (author.getCountry() != null && countryService.findById(author.getCountry().getId()).isPresent()) {
            savedAuthor = Optional.of(authorRepository.save(new Author(author.getName(), author.getSurname(), author.getCountry())));
            applicationEventPublisher.publishEvent(new AuthorChangedEvent(author));
        }
        return savedAuthor;
    }

    @Override
    public void refreshMaterializedView() {
        authorsPerCountryViewRepository.refreshMaterializedView();
    }

    @Override
    public Optional<List<AuthorProjection>> getAuthorsNames() {
        return Optional.of(authorRepository.takeNameAndSurnameByProjection());
    }
}
