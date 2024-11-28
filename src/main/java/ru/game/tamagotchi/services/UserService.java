package ru.game.tamagotchi.services;

import org.springframework.stereotype.Service;
import ru.game.tamagotchi.model.UserEntity;

@Service
public interface UserService {
    UserEntity create(UserEntity user);
    UserEntity findByLogin(String username);
}
