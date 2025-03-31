package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.lab.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    public Optional<Country> findById(Long id);

    public List<Country> getAllCountries();
}
