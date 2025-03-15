package mk.ukim.finki.emt.lab.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.emt.lab.models.Author;
import mk.ukim.finki.emt.lab.models.Category;

@Data
@AllArgsConstructor
public class BookDto {

    String name;
    Category category;
    Long authorId;
    Integer availableCopies;

    public BookDto(){}

}
