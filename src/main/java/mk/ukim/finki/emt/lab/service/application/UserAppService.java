package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.LoginUserDTO;
import mk.ukim.finki.emt.lab.dto.create.CreateUserDto;
import mk.ukim.finki.emt.lab.dto.display.DisplayUserDto;

import java.util.Optional;

public interface UserAppService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDTO loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

}
