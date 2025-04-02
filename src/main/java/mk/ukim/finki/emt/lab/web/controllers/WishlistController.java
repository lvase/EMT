package mk.ukim.finki.emt.lab.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.DecimalMin;
import mk.ukim.finki.emt.lab.dto.WishlistDTO;
import mk.ukim.finki.emt.lab.dto.display.DisplayBookDTO;
import mk.ukim.finki.emt.lab.models.domain.User;
import mk.ukim.finki.emt.lab.exceptions.BookNotAvailableException;
import mk.ukim.finki.emt.lab.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab.service.application.WishlistAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class WishlistController {

    private final WishlistAppService wishlistApplicationService;

    public WishlistController(WishlistAppService wishlistApplicationService) {
        this.wishlistApplicationService = wishlistApplicationService;
    }

    @Operation(summary = "Add book to wishlist", description = "Adds a book to the wishlist for the logged in user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book added to wishlist successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "400", description = "Book not available")
    })
    @PostMapping("/add-book/{id}")
    public ResponseEntity<?> addBookToWishlist(@PathVariable Long id, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return wishlistApplicationService.addBookToWishlist(user.getUsername(), id)
                    .map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (BookNotAvailableException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Operation(summary = "Remove book from wishlist", description = "Remove a book from the wishlist for the logged in user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book removed from wishlist successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping("/remove-book/{id}")
    public ResponseEntity<WishlistDTO> removeBookFromWishlist(@PathVariable Long id, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return wishlistApplicationService.removeBookFromWishlist(user.getUsername(), id)
                    .map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Rent all books from wishlist", description = "Rents all books from the wishlist for the logged in user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books rented successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Request")
    })
    @PutMapping("/rent-all-books/")
    public ResponseEntity<WishlistDTO> rentAllBooksInWishlist(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return wishlistApplicationService.rentAllBooksInWishlist(user.getUsername()).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get active wishlist", description = "Retrieves the active wishlist for the logged in user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wishlist retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Wishlist not found")})
    @GetMapping
    public ResponseEntity<WishlistDTO> getActiveWishlist(HttpServletRequest request) {
        String username = request.getRemoteUser();
        return wishlistApplicationService.getActiveWishlist(username).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Get wishlist list of books", description = "Retrieves list of books added in wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wishlist added books retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Wishlist not found")})
    @GetMapping("/get-books/{id}")
    public ResponseEntity<List<DisplayBookDTO>> getBookById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok((List<DisplayBookDTO>) wishlistApplicationService.listAllBooksInWishlist(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
