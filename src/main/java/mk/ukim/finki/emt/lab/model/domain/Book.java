package mk.ukim.finki.emt.lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.lab.model.enumerations.Category;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Enumerated
    Category category;
    @ManyToOne
    Author author;
    Integer availableCopies;
    Integer timesRented;

    public Book(String name, Category category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.timesRented = 0;
    }

    public Book() {

    }


}
