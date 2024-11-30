package ru.game.tamagotchi.model.fight;

import java.util.Random;

public class Fight {
    private Player player1;
    private Player player2;
    private String result;

    public Fight(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.result = "Fight continues!";

        // По умолчанию ход начинается у игрока 1
        this.player1.setTurn(true);
        this.player2.setTurn(false);
    }

    public void setPlayerAction(String playerName, String action) {
        if (action.equals("attack") || action.equals("defend")) {
            if (playerName.equals(player1.getName())) {
                player1.setAction(action);
            } else if (playerName.equals(player2.getName())) {
                player2.setAction(action);
            }
        }
    }

    public String processRound() {
        Random random = new Random();

        // Если действия обоих игроков уже установлены, то обработаем бой
        if (player1.getAction() != null && player2.getAction() != null) {
            // Атака игрока 1
            if (player1.getAction().equals("attack") && !player2.getAction().equals("defend")) {
                int damage = random.nextInt(20) + 1;  // Генерация случайного урона от 1 до 20
                player2.takeDamage(damage);
            }
            // Атака игрока 2
            if (player2.getAction().equals("attack") && !player1.getAction().equals("defend")) {
                int damage = random.nextInt(20) + 1;  // Генерация случайного урона от 1 до 20
                player1.takeDamage(damage);
            }

            // Проверка состояния здоровья игроков и итог боя
            if (player1.getHealth() <= 0 && player2.getHealth() <= 0) {
                result = "Draw!";
            } else if (player1.getHealth() <= 0) {
                result = player2.getName() + " wins!";
            } else if (player2.getHealth() <= 0) {
                result = player1.getName() + " wins!";
            } else {
                // Если бой продолжается, меняем ход
                toggleTurn();
                result = "Fight continues!";
            }

            // Очистить действия игроков на конец раунда
            player1.setAction(null);
            player2.setAction(null);
        }

        return result;
    }

    private void toggleTurn() {
        // Переключаем ход
        player1.setTurn(!player1.isTurn());
        player2.setTurn(!player2.isTurn());
    }

    // Геттеры и сеттеры

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String getResult() {
        return result;
    }
}
