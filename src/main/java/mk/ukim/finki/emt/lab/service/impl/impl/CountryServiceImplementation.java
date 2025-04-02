package mk.ukim.finki.emt.lab.service.impl.impl;

import mk.ukim.finki.emt.lab.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.lab.models.domain.Country;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.service.impl.CountryServicee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImplementation implements CountryServicee {
    private final CountryRepository countryRepository;

    public CountryServiceImplementation(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) throws CountryNotFoundException {
        return Optional.of(countryRepository.findById(id)).orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country addNewCountry(String name, String continent) {
        return countryRepository.save(new Country(name, continent));
    }

    @Override
    public Country deleteCountry(Long id) {

        Country deletedCoutnry = countryRepository.findById(id).get();
        countryRepository.delete(deletedCoutnry);
        return deletedCoutnry;
    }

    @Override
    public void editCountry(Long id, String name, String continent) throws CountryNotFoundException {

        Country countryToEdit = findById(id).get();
        countryToEdit.setName(name);
        countryToEdit.setContinent(continent);
        countryRepository.save(countryToEdit);
    }
}
