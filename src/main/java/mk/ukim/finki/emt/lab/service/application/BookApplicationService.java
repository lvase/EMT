package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.create.CreateBookDto;
import mk.ukim.finki.emt.lab.dto.display.DisplayBookDto;
import mk.ukim.finki.emt.lab.dto.update.UpdateBookDto;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    public Optional<DisplayBookDto> findById(Long id);

    public List<DisplayBookDto> findAll();

    public Optional<DisplayBookDto> save(CreateBookDto createBookDto);

    public void deleteById(Long id);

    public Optional<DisplayBookDto> update(Long id, UpdateBookDto updateBookDto);

    Optional<DisplayBookDto> mostRentedBook();

}
