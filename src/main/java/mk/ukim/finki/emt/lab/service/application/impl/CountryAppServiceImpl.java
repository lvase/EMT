package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.display.DisplayBookDTO;
import mk.ukim.finki.emt.lab.dto.display.DisplayCountryDTO;
import mk.ukim.finki.emt.lab.service.application.CountryAppService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CountryAppServiceImpl implements CountryAppService {

    private final CountryService countryService;

    public CountryAppServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayCountryDTO> findById(long id) {
        return countryService.findById(id).map(DisplayCountryDTO::from);
    }

    @Override
    public List<DisplayCountryDTO> findAll() {
        return DisplayCountryDTO.from(countryService.getAllCountries());
    }
}
