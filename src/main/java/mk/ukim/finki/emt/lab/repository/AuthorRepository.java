package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.model.projections.AuthorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import mk.ukim.finki.emt.lab.model.domain.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select a.name,a.surname from Author a")
    List<AuthorProjection> takeNameAndSurnameByProjection();
}
