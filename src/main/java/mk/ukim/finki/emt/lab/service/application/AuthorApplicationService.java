package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.create.CreateAuthorDto;
import mk.ukim.finki.emt.lab.dto.display.DisplayAuthorDto;
import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.projections.AuthorProjection;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    Optional<DisplayAuthorDto> findById(Long id);

    Optional<DisplayAuthorDto> save(CreateAuthorDto createAuthorDto);

    List<DisplayAuthorDto> getAllAuthors();

    Optional<DisplayAuthorDto> withMostRentedBooks();

    Optional<List<AuthorProjection>> getAuthorsNames();

}
