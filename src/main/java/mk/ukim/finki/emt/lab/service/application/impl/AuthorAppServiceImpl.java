package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.display.DisplayAuthorDTO;
import mk.ukim.finki.emt.lab.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.service.application.AuthorAppService;
import mk.ukim.finki.emt.lab.service.impl.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorAppServiceImpl implements AuthorAppService {

    private final AuthorService authorService;

    public AuthorAppServiceImpl(AuthorService authorService) {
        this.authorService = authorService;
    }
    @Override
    public Optional<DisplayAuthorDTO> findById(Long id) throws AuthorNotFoundException {
        return authorService.findById(id).map(DisplayAuthorDTO::from);
    }

    @Override
    public List<DisplayAuthorDTO> findAll() {
        return DisplayAuthorDTO.from(authorService.getAllAuthors());
    }
}
