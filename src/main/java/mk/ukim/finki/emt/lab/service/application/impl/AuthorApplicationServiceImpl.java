package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.create.CreateAuthorDto;
import mk.ukim.finki.emt.lab.dto.display.DisplayAuthorDto;
import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.projections.AuthorProjection;
import mk.ukim.finki.emt.lab.service.application.AuthorApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AuthorService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto createAuthorDto) {
        Optional<Country> country = countryService.findById(createAuthorDto.country());
        return country.flatMap(value -> authorService.save(createAuthorDto.toAuthor(value)).map(DisplayAuthorDto::from));
    }

    @Override
    public List<DisplayAuthorDto> getAllAuthors() {
        return DisplayAuthorDto.from(authorService.getAllAuthors());
    }

    @Override
    public Optional<DisplayAuthorDto> withMostRentedBooks() {
        return authorService.withMostRentedBooks().map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<List<AuthorProjection>> getAuthorsNames() {
        return authorService.getAuthorsNames();
    }
}
