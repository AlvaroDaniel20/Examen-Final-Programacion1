package controller;

import model.Player;
import model.PlayerRecord;
import view.GameMenu;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class CharacterController {
    private Scanner scanner;
    private GameMenu gameMenu;
    private GameRecordManager recordManager;

    private Map<String, Integer> characterUnlockRequirements;

    public CharacterController(Scanner scanner, GameMenu gameMenu) {
        this.scanner = scanner;
        this.gameMenu = gameMenu;
        this.recordManager = new GameRecordManager();

        initializeCharacterRequirements();
    }

    private void initializeCharacterRequirements() {
        characterUnlockRequirements = new HashMap<>();
        characterUnlockRequirements.put("Guerrero", 0);
        characterUnlockRequirements.put("Mago", 50);
        characterUnlockRequirements.put("Arquero", 100);
    }

    public Player selectCharacter() {
        int totalPoints = getTotalPointsFromRedords();
        while (true) {
            gameMenu.displayCharacterSelectionMenu(characterUnlockRequirements, totalPoints);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    return createWarrior();
                case 2:
                    if (totalPoints > characterUnlockRequirements.get("Mago")) {
                        return createMage();
                    } else {
                        System.out.println("No tienes suficientes puntos para desbloquear este personaje");
                        break;
                    }
                case 3:
                    if (totalPoints >= characterUnlockRequirements.get("Arquero")) {
                        return createArcher();
                    } else {
                        System.out.println("No tienes suficientes puntos para desbloquear este personaje.");
                        break;
                    }
                case 0:
                    return null;
                default:
                    gameMenu.displayInvalidOptionMessage();
            }
        }
    }

    private int getTotalPointsFromRedords() {
        return recordManager.getAllRecords().stream().mapToInt(PlayerRecord::getPointsEarned).sum();
    }

    private Player createWarrior() {
        return new Player("Guerrero");
    }

    private Player createMage() {
        return new Player("Mago");
    }

    private Player createArcher() {
        return new Player("Arquero");
    }
}