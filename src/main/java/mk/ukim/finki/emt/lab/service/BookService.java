package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.models.Author;
import mk.ukim.finki.emt.lab.models.Book;
import mk.ukim.finki.emt.lab.models.Category;
import mk.ukim.finki.emt.lab.models.UserBook;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public Optional<Book> findById(Long id) throws BookNotFoundException;
    public List<Book> getAllBooks();
    public Book addNewBook(String name, Category category, Long authorID, Integer availableCopies) throws AuthorNotFoundException;
    public Book deleteBook(Long id);
    public void editBook(Long id, String name, Category category, Long authorId, Integer availableCopies) throws BookNotFoundException, AuthorNotFoundException;
    public void borrowBook(Long id, String username) throws BookNotFoundException;
    public List<UserBook> userBooks(Long id);
}
