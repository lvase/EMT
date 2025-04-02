package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.models.domain.User;
import mk.ukim.finki.emt.lab.models.domain.WishList;
import mk.ukim.finki.emt.lab.models.enumerations.WishlistStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<WishList, Long> {
    WishList findByUserAndStatus(User user, WishlistStatus status);
}
