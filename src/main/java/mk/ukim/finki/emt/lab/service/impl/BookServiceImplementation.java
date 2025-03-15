package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.models.Author;
import mk.ukim.finki.emt.lab.models.Book;
import mk.ukim.finki.emt.lab.models.Category;
import mk.ukim.finki.emt.lab.models.UserBook;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.repository.UserBookRepository;
import mk.ukim.finki.emt.lab.service.AuthorService;
import mk.ukim.finki.emt.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final UserBookRepository userBookRepository;

    public BookServiceImplementation(BookRepository bookRepository, AuthorService authorService, UserBookRepository userBookRepository) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.userBookRepository = userBookRepository;
    }

    @Override
    public Optional<Book> findById(Long id) throws BookNotFoundException {
        return Optional.of(bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book addNewBook(String name, Category category, Long authorID, Integer availableCopies) throws AuthorNotFoundException {
        Author author = authorService.findById(authorID).get();
        return bookRepository.save(new Book(name, category, author, availableCopies));
    }

    @Override
    public Book deleteBook(Long id) {
        Book deletedBook = bookRepository.findById(id).get();
        bookRepository.deleteById(id);
        return deletedBook;
    }

    @Override
    public void editBook(Long id, String name, Category category, Long authorId, Integer availableCopies) throws BookNotFoundException, AuthorNotFoundException {
        Book bookToEdit = findById(id).get();
        bookToEdit.setName(name);
        bookToEdit.setCategory(category);
        Author author = authorService.findById(authorId).get();
        bookToEdit.setAuthor(author);
        bookToEdit.setAvailableCopies(availableCopies);
        bookRepository.save(bookToEdit);
    }

    @Override
    public void borrowBook(Long id, String username) throws BookNotFoundException {
        Book borrowedBook = findById(id).get();
        borrowedBook.setAvailableCopies(borrowedBook.getAvailableCopies() - 1);
        bookRepository.save(borrowedBook);
        userBookRepository.save(new UserBook(id, username));
    }

    @Override
    public List<UserBook> userBooks(Long id) {
        List<UserBook> res = userBookRepository.findAllByBookId(id);
        return res;
    }
}
