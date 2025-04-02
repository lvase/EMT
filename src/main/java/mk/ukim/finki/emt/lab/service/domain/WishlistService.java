package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.models.domain.Book;
import mk.ukim.finki.emt.lab.models.domain.WishList;
import mk.ukim.finki.emt.lab.exceptions.BookNotFoundException;
import java.util.List;
import java.util.Optional;

public interface WishlistService {
    Optional<WishList> findById(Long id);

    Optional<WishList> getActiveWishlist(String username);

    List<Book> listAllBooksInWishlist(Long id);

    Optional<WishList> addBookToWishlist(String username, Long bookId) throws BookNotFoundException;

    Optional<WishList> removeBookFromWishlist(String username, Long bookId) throws BookNotFoundException;

    Optional<WishList> rentAllBooksInWishlist(String username);
}
