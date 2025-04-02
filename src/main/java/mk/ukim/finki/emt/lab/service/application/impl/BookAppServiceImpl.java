package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.create.CreateBookDTO;
import mk.ukim.finki.emt.lab.dto.display.DisplayBookDTO;
import mk.ukim.finki.emt.lab.dto.update.UpdateBookDTO;
import mk.ukim.finki.emt.lab.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.models.domain.Author;
import mk.ukim.finki.emt.lab.service.application.BookAppService;
import mk.ukim.finki.emt.lab.service.domain.BookService;
import mk.ukim.finki.emt.lab.service.impl.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookAppServiceImpl implements BookAppService {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookAppServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public Optional<DisplayBookDTO> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDTO::from);
    }

    @Override
    public List<DisplayBookDTO> findAll() {
        return DisplayBookDTO.from(bookService.getAllBooks());
    }

    @Override
    public Optional<DisplayBookDTO> save(CreateBookDTO createBookDTO) throws AuthorNotFoundException {
        Optional<Author> author = authorService.findById(createBookDTO.author());
        return author.flatMap(value -> bookService.save(createBookDTO.toBook(value)).map(DisplayBookDTO::from));
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

    @Override
    public Optional<DisplayBookDTO> update(Long id, UpdateBookDTO updateBookDTO) throws AuthorNotFoundException {
        Optional<Author> author = authorService.findById(updateBookDTO.author());
        return author.flatMap(value -> bookService.update(id, updateBookDTO.toBook(value)).map(DisplayBookDTO::from));
    }
}
