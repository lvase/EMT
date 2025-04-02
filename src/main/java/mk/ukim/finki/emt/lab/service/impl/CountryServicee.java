package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.lab.models.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryServicee {
    public Optional<Country> findById(Long id) throws CountryNotFoundException;
    public List<Country> getAllCountries();
    public Country addNewCountry(String name, String continent);
    public void editCountry(Long id, String name, String continent) throws CountryNotFoundException;
    public Country deleteCountry(Long id);
}
