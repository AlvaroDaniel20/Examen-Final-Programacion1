package controller;

import model.Player;
import model.PlayerRecord;
import model.Enemy;
import view.GameMenu;
import view.PlayerStatsView;
import view.BattleView;

import java.util.List;
import java.util.Scanner;

public class GameController {
    private Scanner scanner;
    private GameMenu gameMenu;
    private PlayerStatsView playerStatsView;
    private BattleView battleView;
    private CharacterController characterController;
    private BattleController battleController;
    private GameRecordManager recordManager;

    public GameController() {
        this.scanner = new Scanner(System.in);
        this.gameMenu = new GameMenu();
        this.playerStatsView = new PlayerStatsView();
        this.battleView = new BattleView(scanner);
        this.characterController = new CharacterController(scanner, gameMenu);
        this.battleController = new BattleController(scanner, battleView);
        this.recordManager = new GameRecordManager();
    }

    public void startGame() {
        Player player = null;
        Enemy enemy = new Enemy("Villano");
        boolean exit = false;

        while (!exit) {
            gameMenu.displayMainMenu();
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    player = characterController.selectCharacter();
                    break;
                case 2:
                    if (player != null) {
                        playerStatsView.displayPlayerStats(player);
                    } else {
                        gameMenu.displayNoCharacterSelectedMessage();
                    }
                    break;
                case 3:
                    if (player != null) {
                        battleController.startBattle(player, enemy);
                    } else {
                        gameMenu.displayNoCharacterSelectedMessage();
                    }
                    break;
                case 4:
                    GameRecordManager recordManager = new GameRecordManager();
                    System.out.println("\n--- TOP 5 MEJORES PUNTUACIONES ---");
                    List<PlayerRecord> topRecords = recordManager.getTopRecords(5);
                    topRecords.forEach(r -> 
                    System.out.printf("Nombre: , Puntos:  ", 
                        r.getPlayerName(), r.getPointsEarned())
                );
                break;
                case 5:
                    gameMenu.displayExitMessage();
                    exit = true;
                    break;
                case 6:
                    if (player != null) {
                        System.out.println("Puntos totales: " + player.getPoints());
                    } else {
                        gameMenu.displayNoCharacterSelectedMessage();
                    }
                    break;
                default:
                    gameMenu.displayInvalidOptionMessage();
            }
        }

        if (player != null) {
            PlayerRecord record = new PlayerRecord(player.getName(), player.getPoints());
            recordManager.savePlayerRecord(record);
            
            System.out.println("\n--- Top 5 Mejores Puntuaciones ---");
            List<PlayerRecord> topRecords = recordManager.getTopRecords(5);
            topRecords.forEach(r -> 
                System.out.printf("Nombre: %s, Puntos: %d%n", 
                    r.getPlayerName(), r.getPointsEarned())
            );
        }
        scanner.close();
    }
}