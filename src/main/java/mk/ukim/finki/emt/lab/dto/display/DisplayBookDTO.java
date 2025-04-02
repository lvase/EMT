package mk.ukim.finki.emt.lab.dto.display;

import mk.ukim.finki.emt.lab.models.domain.Author;
import mk.ukim.finki.emt.lab.models.domain.Book;
import mk.ukim.finki.emt.lab.models.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDTO(Long id,
                            String name,
                             Category category,
                             Long author, Integer availableCopies) {
    public static DisplayBookDTO from (Book book) {
        return new DisplayBookDTO(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }

    public static List<DisplayBookDTO> from(List<Book> books) {
        return books.stream().map(DisplayBookDTO::from).collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        return new Book(name, category, author, availableCopies);
    }
}
