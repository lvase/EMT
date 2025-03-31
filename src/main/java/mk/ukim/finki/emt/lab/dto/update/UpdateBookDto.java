package mk.ukim.finki.emt.lab.dto.update;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateBookDto(
        String name,
        Category category,
        Long author,
        Integer availableCopies
) {
    public static UpdateBookDto from(Book book) {
        return new UpdateBookDto(
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }

    public static List<UpdateBookDto> from(List<Book> books) {
        return books.stream().map(UpdateBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        return new Book(name, category, author, availableCopies);
    }
}
