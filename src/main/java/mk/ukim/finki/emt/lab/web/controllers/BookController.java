package mk.ukim.finki.emt.lab.web.controllers;

import mk.ukim.finki.emt.lab.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.models.Book;
import mk.ukim.finki.emt.lab.models.dto.BookDto;
import mk.ukim.finki.emt.lab.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto book) throws AuthorNotFoundException {

        try {
            Book createdBook = bookService.addNewBook(book.getName(), book.getCategory(), book.getAuthorId(), book.getAvailableCopies());
            return ResponseEntity.ok(createdBook);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/edit/")
    public ResponseEntity editBook(@PathVariable Long id, @RequestBody BookDto book) {

        try {

            bookService.editBook(id, book.getName(), book.getCategory(), book.getAuthorId(), book.getAvailableCopies());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {

        try {
            Book deletedBook = bookService.deleteBook(id);
            return ResponseEntity.ok(deletedBook);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        try {
            Book book = bookService.findById(id).get();
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> booksList = bookService.getAllBooks();
        return ResponseEntity.ok(booksList);
    }
}
