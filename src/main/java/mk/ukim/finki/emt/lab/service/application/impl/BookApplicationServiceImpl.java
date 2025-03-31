package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.create.CreateBookDto;
import mk.ukim.finki.emt.lab.dto.display.DisplayBookDto;
import mk.ukim.finki.emt.lab.dto.update.UpdateBookDto;
import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.service.application.BookApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AuthorService;
import mk.ukim.finki.emt.lab.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return DisplayBookDto.from(bookService.getAllBooks());
    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto createBookDto) {
        Optional<Author> author = authorService.findById(createBookDto.author());
        return author.flatMap(value -> bookService.save(createBookDto.toBook(value)).map(DisplayBookDto::from));
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, UpdateBookDto updateBookDto) {
        Optional<Author> author = authorService.findById(updateBookDto.author());
        return author.flatMap(value -> bookService.update(id, updateBookDto.toBook(value)).map(DisplayBookDto::from));
    }
}
