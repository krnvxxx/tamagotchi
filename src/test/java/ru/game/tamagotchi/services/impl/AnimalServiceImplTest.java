package ru.game.tamagotchi.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.game.tamagotchi.model.AnimalEntity;
import ru.game.tamagotchi.repository.AnimalRepository;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class AnimalServiceImplTest {

    @InjectMocks
    private AnimalServiceImpl animalServiceImpl;

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private UserServiceImpl userService;

    @Test
    @DisplayName("проверка метода create animal")
    void create() {
        AnimalEntity animal = new AnimalEntity();
        animal.setName("Lion");

        Mockito.when(animalRepository.save(animal)).thenReturn(animal);

        AnimalEntity result = animalServiceImpl.create(animal);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Lion", result.getName());
        Mockito.verify(animalRepository, Mockito.times(1)).save(animal);
    }

    @Test
    void status() {
        animalServiceImpl.status();
        Assertions.assertNotNull(animalServiceImpl);
    }

    @Test
    void feed() {
        int id = 1;
        int foodPoints = 50;

        AnimalEntity animal = new AnimalEntity();
        animal.setHungerPoints(70);

        Mockito.when(animalRepository.findById(id)).thenReturn(Optional.of(animal));
        AnimalEntity feed = animalServiceImpl.feed(id, foodPoints);

        Assertions.assertEquals(20, feed.getHungerPoints());
    }

    @Test
    void drink() {
        int id = 1;
        int drinkPoints = 50;

        AnimalEntity animal = new AnimalEntity();
        animal.setThirstPoints(70);

        Mockito.when(animalRepository.findById(id)).thenReturn(Optional.of(animal));
        AnimalEntity thirst = animalServiceImpl.drink(id, drinkPoints);

        Assertions.assertEquals(20, thirst.getThirstPoints());

        Mockito.verify(animalRepository, Mockito.times(1)).findById(id);
    }

}
