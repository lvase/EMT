package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.WishlistDTO;
import mk.ukim.finki.emt.lab.dto.display.DisplayBookDTO;
import mk.ukim.finki.emt.lab.exceptions.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface WishlistAppService {

    Optional<WishlistDTO> findById(Long id);

    Optional<WishlistDTO> getActiveWishlist(String username);

    List<DisplayBookDTO> listAllBooksInWishlist(Long id);

    Optional<WishlistDTO> addBookToWishlist(String username, Long bookId) throws BookNotFoundException, BookNotFoundException;

    Optional<WishlistDTO> removeBookFromWishlist(String username, Long bookId) throws BookNotFoundException;

    Optional<WishlistDTO> rentAllBooksInWishlist(String username);

}
