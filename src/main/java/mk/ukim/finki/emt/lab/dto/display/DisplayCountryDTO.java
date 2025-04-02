package mk.ukim.finki.emt.lab.dto.display;

import mk.ukim.finki.emt.lab.models.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCountryDTO(Long id, String name, String continent) {
    public static DisplayCountryDTO from (Country country) {
        return new DisplayCountryDTO(country.getId(), country.getName(), country.getContinent());
    }
    public static List<DisplayCountryDTO> from(List<Country> countries) {
        return countries.stream().map(DisplayCountryDTO::from).collect(Collectors.toList());
    }

}
