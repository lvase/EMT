package mk.ukim.finki.emt.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mk.ukim.finki.emt.lab.models.Author;
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
