package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.domain.Wishlist;
import mk.ukim.finki.emt.lab.model.enumerations.WishlistStatus;
import mk.ukim.finki.emt.lab.model.exceptions.BookNotAvailableException;
import mk.ukim.finki.emt.lab.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.model.exceptions.WishlistNotFoundException;
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
    public Optional<Wishlist> findById(Long id) {
        return wishlistRepository.findById(id);
    }

    @Override
    public Optional<Wishlist> getActiveWishlist(String username) {
        User user = userService.findByUsername(username);
        Wishlist wishlist = wishlistRepository.findByUserAndStatus(user, WishlistStatus.CREATED);
        return Optional.of(Objects.requireNonNullElseGet(wishlist, () -> wishlistRepository.save(new Wishlist(user))));
    }

    @Override
    public List<Book> listAllBooksInWishlist(Long id) {
        Optional<Wishlist> wishlist = wishlistRepository.findById(id);
        if (wishlist.isPresent()) {
            return wishlist.get().getBooks();
        }
        throw new WishlistNotFoundException(id);
    }

    @Override
    public Optional<Wishlist> addBookToWishlist(String username, Long bookId) throws BookNotFoundException, BookNotAvailableException {
        if (getActiveWishlist(username).isPresent()) {
            Wishlist wishlist = getActiveWishlist(username).get();
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
    public Optional<Wishlist> removeBookFromWishlist(String username, Long bookId) throws BookNotFoundException {
        if (getActiveWishlist(username).isPresent()) {
            Wishlist wishlist = getActiveWishlist(username).get();
            Book book = bookService.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
            if (wishlist.getBooks().stream().anyMatch(b -> b.getId().equals(book.getId()))) {
                wishlist.getBooks().remove(book);
            }
            return Optional.of(wishlistRepository.save(wishlist));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Wishlist> rentAllBooksInWishlist(String username) {
        if (getActiveWishlist(username).isPresent()) {
            Wishlist wishlist = getActiveWishlist(username).get();
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
