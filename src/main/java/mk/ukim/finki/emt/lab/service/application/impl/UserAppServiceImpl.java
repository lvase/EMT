package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.LoginUserDTO;
import mk.ukim.finki.emt.lab.dto.create.CreateUserDto;
import mk.ukim.finki.emt.lab.dto.display.DisplayUserDto;
import mk.ukim.finki.emt.lab.models.domain.User;
import mk.ukim.finki.emt.lab.service.application.UserAppService;
import mk.ukim.finki.emt.lab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAppServiceImpl implements UserAppService {
    private final UserService userService;

    public UserAppServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<DisplayUserDto> login(LoginUserDTO loginUserDto) {
        User user = userService.login(loginUserDto.username(), loginUserDto.password());
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        User user = userService.findByUsername(username);
        return Optional.of(DisplayUserDto.from(user));
    }
}
