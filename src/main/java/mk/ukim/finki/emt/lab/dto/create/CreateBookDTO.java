package mk.ukim.finki.emt.lab.dto.create;

import mk.ukim.finki.emt.lab.models.domain.Author;
import mk.ukim.finki.emt.lab.models.domain.Book;
import mk.ukim.finki.emt.lab.models.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateBookDTO(String name,
                            Category category,
                            Long author, Integer availableCopies) {
    public static CreateBookDTO from (Book book) {
        return new CreateBookDTO(
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }

    public static List<CreateBookDTO> from(List<Book> books) {
        return books.stream().map(CreateBookDTO::from).collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        return new Book(name, category, author, availableCopies);
    }
}
