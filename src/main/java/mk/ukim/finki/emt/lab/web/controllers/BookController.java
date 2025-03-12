package mk.ukim.finki.emt.lab.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.models.Book;
import mk.ukim.finki.emt.lab.models.UserBook;
import mk.ukim.finki.emt.lab.models.dto.BookDto;
import mk.ukim.finki.emt.lab.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book", description = "book API")
@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
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
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDto book) {

        try {

            bookService.editBook(id, book.getName(), book.getCategory(), book.getAuthorId(), book.getAvailableCopies());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{id}/delete")
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

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> booksList = bookService.getAllBooks();
        return ResponseEntity.ok(booksList);
    }

    @PutMapping("/{id}/borrow/")
    public ResponseEntity<Book> borrowBook(@PathVariable Long id, @RequestParam String userName) {
        try {
            bookService.borrowBook(id, userName);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/userBooks/{id}")
    public ResponseEntity<List<UserBook>> getUserBooksByBookId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(bookService.getUserBooks(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
