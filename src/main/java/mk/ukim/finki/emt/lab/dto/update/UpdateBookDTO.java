package mk.ukim.finki.emt.lab.dto.update;

import mk.ukim.finki.emt.lab.models.domain.Author;
import mk.ukim.finki.emt.lab.models.domain.Book;
import mk.ukim.finki.emt.lab.models.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateBookDTO(String name,
                            Category category,
                            Long author, Integer availableCopies) {
    public static UpdateBookDTO from (Book book) {
        return new UpdateBookDTO(
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }

    public static List<UpdateBookDTO> from(List<Book> books) {
        return books.stream().map(UpdateBookDTO::from).collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        return new Book(name, category, author, availableCopies);
    }
}
