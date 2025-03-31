package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.WishlistDto;
import mk.ukim.finki.emt.lab.dto.display.DisplayBookDto;
import mk.ukim.finki.emt.lab.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.service.application.WishlistApplicationService;
import mk.ukim.finki.emt.lab.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistApplicationServiceImpl implements WishlistApplicationService {
    private final WishlistService wishlistService;

    public WishlistApplicationServiceImpl(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public Optional<WishlistDto> findById(Long id) {
        return wishlistService.findById(id).map(WishlistDto::from);
    }

    @Override
    public Optional<WishlistDto> getActiveWishlist(String username) {
        return wishlistService.getActiveWishlist(username).map(WishlistDto::from);
    }

    @Override
    public List<DisplayBookDto> listAllBooksInWishlist(Long id) {
        return DisplayBookDto.from(wishlistService.listAllBooksInWishlist(id));
    }

    @Override
    public Optional<WishlistDto> addBookToWishlist(String username, Long bookId) throws BookNotFoundException {
        return wishlistService.addBookToWishlist(username, bookId).map(WishlistDto::from);
    }

    @Override
    public Optional<WishlistDto> removeBookFromWishlist(String username, Long bookId) throws BookNotFoundException {
        return wishlistService.removeBookFromWishlist(username, bookId).map(WishlistDto::from);
    }

    @Override
    public Optional<WishlistDto> rentAllBooksInWishlist(String username) {
        return wishlistService.rentAllBooksInWishlist(username).map(WishlistDto::from);
    }
}
