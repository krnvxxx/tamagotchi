package ru.game.tamagotchi.services;

import ru.game.tamagotchi.model.AnimalEntity;

import java.util.List;

/**
 *
 * Этот сервис нужен чтобы взаимодействовать с животными.
 *
 * */
public interface AnimalService {
    AnimalEntity create(AnimalEntity animal);
    AnimalEntity status();
    AnimalEntity feed(int id, int foodPoints);
    AnimalEntity drink(int id, int drinkPoints);
}
