package mk.ukim.finki.emt.lab.model.exceptions;

public class WishlistNotFoundException extends RuntimeException {
    public WishlistNotFoundException(Long id) {
        super("Wishlist with id " + id + " not found");
    }
}
