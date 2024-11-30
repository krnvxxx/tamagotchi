package ru.game.tamagotchi.model.fight;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class FightStatus {
    private String player1Name;
    private String player2Name;
    private int player1Health;
    private int player2Health;
    private boolean player1Turn;
    private boolean player2Turn;
    private String result;

    public FightStatus(String result) {
        this.result = result;
    }

    public FightStatus(String player1Name, String player2Name, int player1Health, int player2Health, boolean player1Turn, boolean player2Turn, String result) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player1Health = player1Health;
        this.player2Health = player2Health;
        this.player1Turn = player1Turn;
        this.player2Turn = player2Turn;
        this.result = result;
    }

    // Утилитарный метод для отображения всего состояния боя в одном строковом представлении
    @Override
    public String toString() {
        return "FightStatus{" +
                "player1Name='" + player1Name + '\'' +
                ", player2Name='" + player2Name + '\'' +
                ", player1Health=" + player1Health +
                ", player2Health=" + player2Health +
                ", player1Turn=" + player1Turn +
                ", player2Turn=" + player2Turn +
                ", result='" + result + '\'' +
                '}';
    }
}
