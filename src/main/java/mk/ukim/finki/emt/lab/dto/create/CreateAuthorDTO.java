package mk.ukim.finki.emt.lab.dto.create;

import mk.ukim.finki.emt.lab.models.domain.Author;
import mk.ukim.finki.emt.lab.models.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAuthorDTO(String name, String surname, Long countryID) {

    public static CreateAuthorDTO from (Author author) {
        return new CreateAuthorDTO(
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }
    public static List<CreateAuthorDTO> from (List<Author> authors) {
        return authors.stream().map(CreateAuthorDTO::from).collect(Collectors.toList());
    }

    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }
}
