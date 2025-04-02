package mk.ukim.finki.emt.lab.dto.update;

import mk.ukim.finki.emt.lab.models.domain.Author;
import mk.ukim.finki.emt.lab.models.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateAuthorDTO(String name, String surname, Long countryID) {

    public static UpdateAuthorDTO from (Author author) {
        return new UpdateAuthorDTO(
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }
    public static List<UpdateAuthorDTO> from (List<Author> authors) {
        return authors.stream().map(UpdateAuthorDTO::from).collect(Collectors.toList());
    }

    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }
}
