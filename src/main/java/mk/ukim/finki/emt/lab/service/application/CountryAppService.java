package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.display.DisplayCountryDTO;

import java.util.List;
import java.util.Optional;

public interface CountryAppService {

    public Optional<DisplayCountryDTO> findById(long id);
    public List<DisplayCountryDTO> findAll();
}
