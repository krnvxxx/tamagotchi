package ru.game.tamagotchi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Data
@NoArgsConstructor
@Entity(name = "animals")
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AnimalType type;

    private String name;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity owner;

    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth = LocalDateTime.now();

    @Column(name = "health_points")
    private Integer healthPoints = 100;

    @Column(name = "hunger_points")
    private Integer hungerPoints = 0;

    @Column(name = "thirst_points")
    private Integer thirstPoints = 0;

    public int getAge() {
        return (dateOfBirth != null)
                ? Period.between(dateOfBirth.toLocalDate(), LocalDate.now()).getYears()
                : 0;
    }
}




