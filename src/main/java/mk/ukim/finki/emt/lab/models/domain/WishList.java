package mk.ukim.finki.emt.lab.models.domain;


import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.lab.models.enumerations.WishlistStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class WishList {
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

    public WishList() {
    }

    public WishList(User user) {
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.books = new ArrayList<>();
        this.status = WishlistStatus.CREATED;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public User getUser() {
        return user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public WishlistStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setStatus(WishlistStatus status) {
        this.status = status;
    }
}
