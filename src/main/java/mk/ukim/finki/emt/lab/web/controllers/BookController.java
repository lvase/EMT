package mk.ukim.finki.emt.lab.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.dto.create.CreateBookDto;
import mk.ukim.finki.emt.lab.dto.display.DisplayBookDto;
import mk.ukim.finki.emt.lab.dto.update.UpdateBookDto;
import mk.ukim.finki.emt.lab.model.views.BooksPerAuthorView;
import mk.ukim.finki.emt.lab.repository.BooksPerAuthorViewRepository;
import mk.ukim.finki.emt.lab.service.application.BookApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book", description = "book API")
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookApplicationService bookApplicationService;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;


    public BookController(BookApplicationService bookApplicationService, BooksPerAuthorViewRepository booksPerAuthorViewRepository) {
        this.bookApplicationService = bookApplicationService;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
    }

    @Operation(summary = "Add a new book", description = "Creates a new book based on the given BookDto.")
    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> save(@RequestBody CreateBookDto createBookDto) {
        return bookApplicationService.save(createBookDto).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an existing book", description = "Updates a  book by Id using BookDto.")
    @PutMapping("/{id}/edit/")
    public ResponseEntity<DisplayBookDto> update(@PathVariable Long id, @RequestBody UpdateBookDto updateBookDto) {

        return bookApplicationService.update(id, updateBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Delete a book", description = "Deletes a book by Id.")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (bookApplicationService.findById(id).isPresent()) {
            bookApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get a book by Id", description = "Finds a book by Id. ")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return bookApplicationService.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all books", description = "Retrieves a list of all books.")
    @GetMapping
    public ResponseEntity<List<DisplayBookDto>> findAll() {
        return ResponseEntity.ok(bookApplicationService.findAll());
    }

    @Operation(summary = "Get most rented book", description = "Retrieves the book that is rented the most times")
    @GetMapping("/mostRented")
    public ResponseEntity<DisplayBookDto> getMostRented() {
        return bookApplicationService.mostRentedBook().
                map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Get number of books per author",description = "Retrieves a list of number of books grouped by author")
    @GetMapping("/by-author")
    public ResponseEntity<List<BooksPerAuthorView>> getBooksPerAuthor() {
        return ResponseEntity.ok(booksPerAuthorViewRepository.findAll());
    }

}
