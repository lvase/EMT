package mk.ukim.finki.emt.lab.models;

import jakarta.persistence.*;

@Entity
public class UserBook {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookId;
    private String name;

    public UserBook(Long bookId, String name) {
        this.bookId = bookId;
        this.name = name;
    }


    public UserBook() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

