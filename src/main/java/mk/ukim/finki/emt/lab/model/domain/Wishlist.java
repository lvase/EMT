package mk.ukim.finki.emt.lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.lab.model.enumerations.WishlistStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCreated;
    @ManyToOne
    User user;
    @ManyToMany
    List<Book> books;
    @Enumerated(value = EnumType.STRING)
    WishlistStatus status;

    public Wishlist() {
    }

    public Wishlist(User user) {
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.books = new ArrayList<>();
        this.status = WishlistStatus.CREATED;
    }
}
