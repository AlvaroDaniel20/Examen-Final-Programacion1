package controller;

import model.Player;
import view.GameMenu;
import java.util.Scanner;

public class CharacterController {
    private Scanner scanner;
    private GameMenu gameMenu;

    public CharacterController(Scanner scanner, GameMenu gameMenu) {
        this.scanner = scanner;
        this.gameMenu = gameMenu;
    }

    public Player selectCharacter() {
        gameMenu.displayCharacterSelectionMenu();
        int characterChoice = scanner.nextInt();
        Player player;

        switch (characterChoice) {
            case 1:
                player = new Player("Guerrero");
                break;
            case 2:
                player = new Player("Mago");
                break;
            case 3:
                player = new Player("Arquero");
                break;
            default:
                gameMenu.displayDefaultCharacterSelectedMessage();
                player = new Player("Guerrero");
                break;
        }
        gameMenu.displayCharacterSelectedMessage(player.getName());
        return player;
    }
}