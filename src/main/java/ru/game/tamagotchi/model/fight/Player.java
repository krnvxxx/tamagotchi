package ru.game.tamagotchi.model.fight;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String name;
    private int health;
    private String action;  // Действие игрока (атака или защита)
    private boolean turn;  // Новый флаг для определения, чей сейчас ход

    public Player(String name) {
        this.name = name;
        this.health = 100;  // Здоровье по умолчанию
        this.action = null; // Действие по умолчанию
        this.turn = false;  // По умолчанию ход не у игрока
    }

    // Метод для проверки, чей ход
    public boolean isTurn() {
        return turn;
    }

    // Установить ход
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    // Нанесение урона
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    // Геттеры и сеттеры

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
