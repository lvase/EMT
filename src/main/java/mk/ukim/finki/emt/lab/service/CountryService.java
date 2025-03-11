package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.lab.models.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    public Optional<Country> findById(Long id) throws CountryNotFoundException;
    public List<Country> getAllCountries();
}
