package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.domain.Wishlist;
import mk.ukim.finki.emt.lab.model.exceptions.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface WishlistService {
    Optional<Wishlist> findById(Long id);

    Optional<Wishlist> getActiveWishlist(String username);

    List<Book> listAllBooksInWishlist(Long id);

    Optional<Wishlist> addBookToWishlist(String username, Long bookId) throws BookNotFoundException;

    Optional<Wishlist> removeBookFromWishlist(String username, Long bookId) throws BookNotFoundException;

    Optional<Wishlist> rentAllBooksInWishlist(String username);
}
