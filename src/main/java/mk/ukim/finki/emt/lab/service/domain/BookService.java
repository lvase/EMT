package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public Optional<Book> findById(Long id);

    public List<Book> getAllBooks();

    public Optional<Book> save(Book book);

    public void deleteById(Long id);

    public Optional<Book> update(Long id, Book book);

}
