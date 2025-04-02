package mk.ukim.finki.emt.lab.dto.display;

import mk.ukim.finki.emt.lab.models.domain.User;
import mk.ukim.finki.emt.lab.models.enumerations.Role;

public record DisplayUserDto(String username, String name, String surname, Role role) {
    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }

    public User toUser() {
        return new User(username, name, surname, role.name());
    }

}