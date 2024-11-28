package ru.game.tamagotchi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.game.tamagotchi.model.AnimalEntity;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, Integer> {

}
