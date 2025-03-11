package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id) throws AuthorNotFoundException;
    List<Author> getAllAuthors();
}
