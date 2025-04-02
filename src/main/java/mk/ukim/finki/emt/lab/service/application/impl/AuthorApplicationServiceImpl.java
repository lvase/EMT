package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.display.DisplayAuthorDto;
import mk.ukim.finki.emt.lab.service.application.AuthorApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;

    public AuthorApplicationServiceImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public List<DisplayAuthorDto> getAllAuthors() {
        return DisplayAuthorDto.from(authorService.getAllAuthors());
    }

    @Override
    public Optional<DisplayAuthorDto> withMostRentedBooks() {
        return authorService.withMostRentedBooks().map(DisplayAuthorDto::from);
    }
}
