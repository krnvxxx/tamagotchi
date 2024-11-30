package ru.game.tamagotchi.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.game.tamagotchi.model.fight.Fight;
import ru.game.tamagotchi.model.fight.FightMessage;
import ru.game.tamagotchi.model.fight.FightStatus;
import ru.game.tamagotchi.model.fight.Player;

import java.util.HashMap;
import java.util.Map;

@Controller
public class GameController {

    private Map<String, Player> players = new HashMap<>();
    private Fight fight;

    // Обработчик для отправки действий игроков
    @MessageMapping("/fight")
    @SendTo("/topic/fight") // Отправляем данные всем подписанным клиентам
    public FightStatus processFightAction(FightMessage fightAction) {
        String playerName = fightAction.getPlayerName();
        String action = fightAction.getAction();

        // Если игрока нет в мапе, создаем его
        if (!players.containsKey(playerName)) {
            players.put(playerName, new Player(playerName));
        }

        // Получаем игрока по имени
        Player player = players.get(playerName);
        if (player == null) {
            // Если игрок не найден, отправляем сообщение об ошибке
            return new FightStatus("Player not found.");
        }

        // Инициализация боя, если он еще не был создан
        if (fight == null) {
            // Проверяем, что в мапе есть два игрока
            if (players.size() == 2) {
                String player1Name = (String) players.keySet().toArray()[0]; // Получаем имя первого игрока
                String player2Name = (String) players.keySet().toArray()[1]; // Получаем имя второго игрока

                Player player1 = players.get(player1Name);  // Получаем первого игрока по имени
                Player player2 = players.get(player2Name);  // Получаем второго игрока по имени

                // Создаем бой с игроками
                fight = new Fight(player1, player2);
            } else {
                return new FightStatus("Waiting for players...");
            }
        }

        // Устанавливаем действие игрока в бой
        fight.setPlayerAction(playerName, action);

        // Проверяем, чей сейчас ход
        Player currentPlayer = fight.getPlayer1().isTurn() ? fight.getPlayer1() : fight.getPlayer2();
        if (!currentPlayer.getName().equals(playerName)) {
            return new FightStatus("It's not your turn yet!");
        }

        // Обрабатываем раунд
        String result = fight.processRound();

        // Проверяем, если кто-то победил или есть ничья
        if (fight.getPlayer1().getHealth() <= 0 && fight.getPlayer2().getHealth() <= 0) {
            result = "Draw!";
        } else if (fight.getPlayer1().getHealth() <= 0) {
            result = fight.getPlayer2().getName() + " wins!";
        } else if (fight.getPlayer2().getHealth() <= 0) {
            result = fight.getPlayer1().getName() + " wins!";
        }

        // Возвращаем статус боя с результатом
        return new FightStatus(
                fight.getPlayer1().getName(),
                fight.getPlayer2().getName(),
                fight.getPlayer1().getHealth(),
                fight.getPlayer2().getHealth(),
                fight.getPlayer1().isTurn(),
                fight.getPlayer2().isTurn(),
                result
        );
    }
}


