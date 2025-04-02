package mk.ukim.finki.emt.lab.dto.display;

import mk.ukim.finki.emt.lab.models.domain.Author;
import mk.ukim.finki.emt.lab.models.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAuthorDTO(Long id, String name, String surname, Long countryID) {

    public static DisplayAuthorDTO from (Author author) {
        return new DisplayAuthorDTO(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }
    public static List<DisplayAuthorDTO> from (List<Author> authors) {
        return authors.stream().map(DisplayAuthorDTO::from).collect(Collectors.toList());
    }

    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }
}
