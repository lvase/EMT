package mk.ukim.finki.emt.lab.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long bookId;
    String userName;

    public UserBook(Long bookId, String userName) {
        this.bookId = bookId;
        this.userName = userName;
    }
   public UserBook() {}
}
