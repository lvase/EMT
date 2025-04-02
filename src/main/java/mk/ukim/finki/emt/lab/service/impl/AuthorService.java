package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.lab.models.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id) throws AuthorNotFoundException;
    List<Author> getAllAuthors();
    Author addAuthor(String name, String surname, Long countryID) throws CountryNotFoundException;
    void editAuthor(Long id, String name, String surname, Long countryId) throws CountryNotFoundException, AuthorNotFoundException;
    Author deleteAuthor(Long id);

}
