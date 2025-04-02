package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.display.DisplayAuthorDto;
import mk.ukim.finki.emt.lab.model.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    Optional<DisplayAuthorDto> findById(Long id);

    List<DisplayAuthorDto> getAllAuthors();

    Optional<DisplayAuthorDto> withMostRentedBooks();
}
