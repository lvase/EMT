package mk.ukim.finki.emt.lab.dto.display;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDto(
        Long id,
        String name,
        Category category,
        Long author,
        Integer availableCopies,
        Integer timesRented
) {
    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies(),
                book.getTimesRented()
        );
    }

    public static List<DisplayBookDto> from(List<Book> books) {
        return books.stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        return new Book(name, category, author, availableCopies);
    }

}
