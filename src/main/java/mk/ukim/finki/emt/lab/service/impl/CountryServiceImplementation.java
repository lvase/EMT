package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.lab.models.Country;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.service.CountryService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImplementation implements CountryService {
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
}
