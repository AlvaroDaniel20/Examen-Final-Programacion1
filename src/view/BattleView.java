package view;

import model.Player;
import model.Enemy;
import model.Item;
import java.util.Scanner;

public class BattleView {
    @SuppressWarnings("unused")
    private Scanner scanner;

    public BattleView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayBattleStart(Player player, Enemy enemy) {
        System.out.println("¡La batalla entre " + player.getName() + " y " + enemy.getName() + " ha comenzado!");
    }

    public void displayBattleMenu(Player player, boolean specialAttackAvailable, boolean superGuerreroAvailable) {
        System.out.println("\n------------------------------------------------");
        System.out.println("\nTu turno, " + player.getName() + ":");
        System.out.println("1. Ataque basico");

        if (specialAttackAvailable) {
            System.out.println("2. Ataque especial");
        }

        System.out.println("3. Defensa");

        if (superGuerreroAvailable) {
            System.out.println("4. Super Guerrero");
        }

        System.out.println("Elige tu acción:");
    }

    public void displayItemFound(Item item) {
        System.out.println("¡Haz encontrado un objeto especial: " + item.getName() + "!");
        System.out.println("Deseas usarlo ahora? (1: Si | 2: No)");
    }

    public void displayBattleResult(boolean playerWon, String playerName, String enemyName) {
        if (playerWon) {
            System.out.println();
            System.out.println("--------" + enemyName + " ha sido derrotado. ¡Ganaste la batalla!--------");
        } else {
            System.out.println();
            System.out.println("--------" + playerName + " ha sido derrotado. ¡Perdiste la batalla!--------");
        }
    }

    public void displayInvalidActionMessage() {
        System.out.println("Acción no valida, pierdes el turno.");
    }

    public void displayInvalidInputMessage() {
        System.out.println("Entrada no valida, ingresa un numero del 1 al 4.");
    }

    public void displayPointsEarned(int points) {
        System.out.println("¡Ganaste " + points + " puntos en esta batalla!");
    }
}