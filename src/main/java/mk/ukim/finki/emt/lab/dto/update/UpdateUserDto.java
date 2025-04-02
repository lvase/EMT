package mk.ukim.finki.emt.lab.dto.update;

import mk.ukim.finki.emt.lab.models.domain.User;
import mk.ukim.finki.emt.lab.models.enumerations.Role;

public record UpdateUserDto(String username,
                            String password,
                            String repeatPassword,
                            String name,
                            String surname,
                            Role role
) {

    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}
