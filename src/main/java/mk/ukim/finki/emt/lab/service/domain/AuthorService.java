package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.model.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id);

    List<Author> getAllAuthors();

    Optional<Author> withMostRentedBooks();
}
