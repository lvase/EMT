package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.exceptions.BookNotAvailableException;
import mk.ukim.finki.emt.lab.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.exceptions.WishlistNotFoundException;
import mk.ukim.finki.emt.lab.models.domain.Book;
import mk.ukim.finki.emt.lab.models.domain.User;
import mk.ukim.finki.emt.lab.models.domain.WishList;
import mk.ukim.finki.emt.lab.models.enumerations.WishlistStatus;
import mk.ukim.finki.emt.lab.repository.WishlistRepository;
import mk.ukim.finki.emt.lab.service.domain.BookService;
import mk.ukim.finki.emt.lab.service.domain.UserService;
import mk.ukim.finki.emt.lab.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserService userService;
    private final BookService bookService;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, UserService userService, BookService bookService) {
        this.wishlistRepository = wishlistRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public Optional<WishList> findById(Long id) {
        return wishlistRepository.findById(id);
    }

    @Override
    public Optional<WishList> getActiveWishlist(String username) {
        User user = userService.findByUsername(username);
        WishList wishList = wishlistRepository.findByUserAndStatus(user, WishlistStatus.CREATED);
        return Optional.of(Objects.requireNonNullElseGet(wishList, () -> wishlistRepository.save(new WishList(user))));

    }

    @Override
    public List<Book> listAllBooksInWishlist(Long id) {
        Optional<WishList> wishList = wishlistRepository.findById(id);
        if (wishList.isPresent()) {
            return wishList.get().getBooks();
        }
        throw new WishlistNotFoundException(id);
    }

    @Override
    public Optional<WishList> addBookToWishlist(String username, Long bookId) throws BookNotFoundException, BookNotAvailableException {
        if (getActiveWishlist(username).isPresent()) {
            WishList wishlist = getActiveWishlist(username).get();
            Book book = bookService.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
            if (wishlist.getBooks().stream().noneMatch(b -> b.getId().equals(book.getId()))) {
                if (book.getAvailableCopies() <= 0) {
                    throw new BookNotAvailableException(bookId);
                }
                wishlist.getBooks().add(book);
            }
            return Optional.of(wishlistRepository.save(wishlist));
        }
        return Optional.empty();
    }

    @Override
    public Optional<WishList> removeBookFromWishlist(String username, Long bookId) throws BookNotFoundException {
        if (getActiveWishlist(username).isPresent()) {
            WishList wishlist = getActiveWishlist(username).get();
            Book book = bookService.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
            if (wishlist.getBooks().stream().anyMatch(b -> b.getId().equals(book.getId()))) {
                wishlist.getBooks().remove(book);
            }
            return Optional.of(wishlistRepository.save(wishlist));
        }
        return Optional.empty();
    }

    @Override
    public Optional<WishList> rentAllBooksInWishlist(String username) {
        if (getActiveWishlist(username).isPresent()) {
            WishList wishlist = getActiveWishlist(username).get();
            wishlist.getBooks().forEach(book -> {
                        book.setAvailableCopies(book.getAvailableCopies() - 1);
                        bookService.update(book.getId(), book);
                    }
            );
            wishlist.setStatus(WishlistStatus.FINISHED);
            return Optional.of(wishlistRepository.save(wishlist));
        }
        return Optional.empty();
    }
}
