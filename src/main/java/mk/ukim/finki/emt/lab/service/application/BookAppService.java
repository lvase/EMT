package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.create.CreateBookDTO;
import mk.ukim.finki.emt.lab.dto.display.DisplayBookDTO;
import mk.ukim.finki.emt.lab.dto.update.UpdateBookDTO;
import mk.ukim.finki.emt.lab.exceptions.AuthorNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookAppService {
    public Optional<DisplayBookDTO> findById(Long id);
    public List<DisplayBookDTO> findAll();
    public Optional<DisplayBookDTO> save(CreateBookDTO createBookDTO) throws AuthorNotFoundException;
    public void deleteById(Long id);
    public Optional<DisplayBookDTO> update(Long id, UpdateBookDTO updateBookDto) throws AuthorNotFoundException;

}
