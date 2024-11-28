package ru.game.tamagotchi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.game.tamagotchi.model.AnimalEntity;
import ru.game.tamagotchi.services.AnimalService;


@Controller
@RequestMapping("/api/v1/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @PostMapping("/create")
    public AnimalEntity create(@RequestBody AnimalEntity animal) {
        return animalService.create(animal);
    }

    @GetMapping("/status")
    public String status(Model model) {
        model.addAttribute("tamagotchi", animalService.status());
        return "tamagotchis";
    }

    @PatchMapping("/feed/{id}")
    public AnimalEntity feed(@PathVariable int id, @RequestParam int foodPoints) {
        return animalService.feed(id, foodPoints);
    }

    @PatchMapping("/drink/{id}")
    public AnimalEntity drink(@PathVariable int id, @RequestParam int drinkPoints) {
        return animalService.drink(id, drinkPoints);
    }
}
