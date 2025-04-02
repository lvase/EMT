package mk.ukim.finki.emt.lab.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.dto.create.CreateBookDTO;
import mk.ukim.finki.emt.lab.dto.display.DisplayBookDTO;
import mk.ukim.finki.emt.lab.dto.update.UpdateBookDTO;
import mk.ukim.finki.emt.lab.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.models.domain.Book;
import mk.ukim.finki.emt.lab.models.domain.UserBook;
import mk.ukim.finki.emt.lab.dto.BookDto;
import mk.ukim.finki.emt.lab.repository.UserBookRepository;
import mk.ukim.finki.emt.lab.service.application.BookAppService;
import mk.ukim.finki.emt.lab.service.impl.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book", description = "book API")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookAppService bookAppService;

    public BookController(BookAppService bookApplicationService) {
        this.bookAppService = bookApplicationService;
    }

    @Operation(summary = "Add a new book", description = "Creates a new book based on the given BookDto.")
    @PostMapping("/add")
    public ResponseEntity<DisplayBookDTO> save(@RequestBody CreateBookDTO createBookDto) throws AuthorNotFoundException {
        return bookAppService.save(createBookDto).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an existing book", description = "Updates a  book by Id using BookDto.")
    @PutMapping("/{id}/edit/")
    public ResponseEntity<DisplayBookDTO> update(@PathVariable Long id, @RequestBody UpdateBookDTO updateBookDto) throws AuthorNotFoundException {

        return bookAppService.update(id, updateBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Delete a book", description = "Deletes a book by Id.")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (bookAppService.findById(id).isPresent()) {
            bookAppService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get a book by Id", description = "Finds a book by Id. ")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDTO> findById(@PathVariable Long id) {
        return bookAppService.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all books", description = "Retrieves a list of all books.")
    @GetMapping
    public ResponseEntity<List<DisplayBookDTO>> findAll() {
        return ResponseEntity.ok(bookAppService.findAll());
    }
}
