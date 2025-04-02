package mk.ukim.finki.emt.lab.dto.update;

import mk.ukim.finki.emt.lab.models.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateCountryDTO(String name, String continent) {
    public static UpdateCountryDTO from (Country country) {
        return new UpdateCountryDTO(country.getName(), country.getContinent());
    }
    public static List<UpdateCountryDTO> from(List<Country> countries) {
        return countries.stream().map(UpdateCountryDTO::from).collect(Collectors.toList());
    }

}
