package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.models.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id);
    List<Author> findAll();
}
