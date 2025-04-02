package mk.ukim.finki.emt.lab.dto.create;

import mk.ukim.finki.emt.lab.models.domain.Book;
import mk.ukim.finki.emt.lab.models.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record CreateCountryDTO(String name, String continent) {
    public static CreateCountryDTO from (Country country) {
        return new CreateCountryDTO(country.getName(), country.getContinent());
    }
    public static List<CreateCountryDTO> from(List<Country> countries) {
        return countries.stream().map(CreateCountryDTO::from).collect(Collectors.toList());
    }

}
