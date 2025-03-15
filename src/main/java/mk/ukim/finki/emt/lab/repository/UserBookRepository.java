package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.models.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Long> {
    List<UserBook> findAllByBookId(Long bookId);
}
