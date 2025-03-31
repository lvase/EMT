package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.display.DisplayCountryDto;
import mk.ukim.finki.emt.lab.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    public Optional<DisplayCountryDto> findById(Long id);

    public List<DisplayCountryDto> getAllCountries();
}
