package ru.game.tamagotchi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.game.tamagotchi.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByLogin(String username);
}
