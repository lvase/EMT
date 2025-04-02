package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.WishlistDTO;
import mk.ukim.finki.emt.lab.dto.display.DisplayBookDTO;
import mk.ukim.finki.emt.lab.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.service.application.WishlistAppService;
import mk.ukim.finki.emt.lab.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistAppServiceImpl implements WishlistAppService {
    private final WishlistService wishlistService;

    public WishlistAppServiceImpl(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public Optional<WishlistDTO> findById(Long id) {
        return wishlistService.findById(id).map(WishlistDTO::from);
    }

    @Override
    public Optional<WishlistDTO> getActiveWishlist(String username) {
        return wishlistService.getActiveWishlist(username).map(WishlistDTO::from);
    }

    @Override
    public List<DisplayBookDTO> listAllBooksInWishlist(Long id) {
        return DisplayBookDTO.from(wishlistService.listAllBooksInWishlist(id));
    }

    @Override
    public Optional<WishlistDTO> addBookToWishlist(String username, Long bookId) throws BookNotFoundException {
        return wishlistService.addBookToWishlist(username, bookId).map(WishlistDTO::from);
    }

    @Override
    public Optional<WishlistDTO> removeBookFromWishlist(String username, Long bookId) throws BookNotFoundException {
        return wishlistService.removeBookFromWishlist(username, bookId).map(WishlistDTO::from);
    }

    @Override
    public Optional<WishlistDTO> rentAllBooksInWishlist(String username) {
        return wishlistService.rentAllBooksInWishlist(username).map(WishlistDTO::from);
    }
}
