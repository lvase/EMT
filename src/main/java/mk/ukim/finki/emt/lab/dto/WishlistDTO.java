package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.dto.display.DisplayBookDTO;
import mk.ukim.finki.emt.lab.dto.display.DisplayUserDto;
import mk.ukim.finki.emt.lab.models.domain.WishList;

import java.time.LocalDateTime;
import java.util.List;

public record WishlistDTO (Long id,
                           LocalDateTime dateCreated,
                           DisplayUserDto user,
                           List<DisplayBookDTO> books
) {
    public static WishlistDTO from(WishList wishlist) {
        return new WishlistDTO(
                wishlist.getId(),
                wishlist.getDateCreated(),
                DisplayUserDto.from(wishlist.getUser()),
                DisplayBookDTO.from(wishlist.getBooks())
        );
    }
}
