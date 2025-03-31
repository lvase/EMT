package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.WishlistDto;
import mk.ukim.finki.emt.lab.dto.display.DisplayBookDto;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.domain.Wishlist;
import mk.ukim.finki.emt.lab.model.exceptions.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface WishlistApplicationService {
    Optional<WishlistDto> findById(Long id);

    Optional<WishlistDto> getActiveWishlist(String username);

    List<DisplayBookDto> listAllBooksInWishlist(Long id);

    Optional<WishlistDto> addBookToWishlist(String username, Long bookId) throws BookNotFoundException;

    Optional<WishlistDto> removeBookFromWishlist(String username, Long bookId) throws BookNotFoundException;

    Optional<WishlistDto> rentAllBooksInWishlist(String username);
}
