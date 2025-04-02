package mk.ukim.finki.emt.lab.dto.create;

import mk.ukim.finki.emt.lab.models.enumerations.Role;
import mk.ukim.finki.emt.lab.models.domain.User;
public record CreateUserDto(String username,
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
