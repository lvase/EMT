package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.domain.Wishlist;
import mk.ukim.finki.emt.lab.model.enumerations.WishlistStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUserAndStatus(User user, WishlistStatus status);
}
