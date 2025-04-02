package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.models.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    public Optional<Country> findById(Long id);

    public List<Country> getAllCountries();
}
