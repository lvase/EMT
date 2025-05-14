package mk.ukim.finki.emt.lab.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.dto.display.DisplayAuthorDto;
import mk.ukim.finki.emt.lab.model.projections.AuthorProjection;
import mk.ukim.finki.emt.lab.model.views.AuthorsPerCountryView;
import mk.ukim.finki.emt.lab.repository.AuthorsPerCountryRepository;
import mk.ukim.finki.emt.lab.service.application.AuthorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author API", description = "Endpoints for managing authors")

public class AuthorController {
    private final AuthorApplicationService authorApplicationService;
    private final AuthorsPerCountryRepository authorsPerCountryRepository;

    public AuthorController(AuthorApplicationService authorApplicationService, AuthorsPerCountryRepository authorsPerCountryRepository) {
        this.authorApplicationService = authorApplicationService;
        this.authorsPerCountryRepository = authorsPerCountryRepository;
    }

    @Operation(summary = "Gets all authors", description = "Retrieves a list of all the authors")
    @GetMapping
    public ResponseEntity<List<DisplayAuthorDto>> findAll() {
        return ResponseEntity.ok(authorApplicationService.getAllAuthors());
    }

    @Operation(summary = "Gets author with most rented books", description = "Retrieves an author whose books are rented the most times")
    @GetMapping("/withMostRentedBooks")
    public ResponseEntity<DisplayAuthorDto> withMostRentedBooks() {
        return authorApplicationService.withMostRentedBooks()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Gets authors per country", description = "Retrieves list of num of authors grouped by country")
    @GetMapping("/by-country")
    public ResponseEntity<List<AuthorsPerCountryView>> getAuthorsPerCountry() {
        return ResponseEntity.ok(authorsPerCountryRepository.findAll());
    }

    @Operation(summary = "Gets authors names", description = "Retrieves list of authors name and surname")
    @GetMapping("/names")
    public ResponseEntity<Optional<List<AuthorProjection>>> getAuthorsNames() {
        return ResponseEntity.ok(authorApplicationService.getAuthorsNames());
    }
}
