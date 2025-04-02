package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.display.DisplayAuthorDTO;
import mk.ukim.finki.emt.lab.exceptions.AuthorNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AuthorAppService {

    Optional<DisplayAuthorDTO> findById(Long id) throws AuthorNotFoundException;
    List<DisplayAuthorDTO> findAll();
}
