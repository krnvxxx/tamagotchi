package ru.game.tamagotchi.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.game.tamagotchi.model.AnimalEntity;
import ru.game.tamagotchi.model.UserEntity;
import ru.game.tamagotchi.repository.AnimalRepository;
import ru.game.tamagotchi.services.AnimalService;
import ru.game.tamagotchi.services.UserService;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final UserService userService;

    @Override
    public AnimalEntity create(AnimalEntity animal) {
        animalRepository.save(animal);
        return animal;
    }

    @Override
    public AnimalEntity status() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity byUsername = userService.findByLogin(user.getUsername());
        AnimalEntity animal = byUsername.getAnimal();

        return animal;
    }

    @Transactional
    @Override
    public AnimalEntity feed(int id, int foodPoints) {
        AnimalEntity animal = animalRepository.findById(id).get();
        int newHungerLevel = Math.max(0, Math.min(animal.getHungerPoints() - foodPoints, 100));
        animal.setHungerPoints(newHungerLevel);

        return animal;
    }

    @Transactional
    @Override
    public AnimalEntity drink(int id, int drinkPoints) {
        AnimalEntity animal = animalRepository.findById(id).get();
        int newThirstPoints = Math.max(0, Math.min(animal.getThirstPoints() - drinkPoints, 100));
        animal.setThirstPoints(newThirstPoints);

        return animal;
    }
}
