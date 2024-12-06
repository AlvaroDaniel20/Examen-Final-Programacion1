package controller;

import model.Player;
import model.Enemy;
import model.Item;
import view.BattleView;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class BattleController {
    private Scanner scanner;
    private BattleView battleView;
    private ItemController itemController;
    private Random random;

    public BattleController(Scanner scanner, BattleView battleView) {
        this.scanner = scanner;
        this.battleView = battleView;
        this.itemController = new ItemController();
        this.random = new Random();
    }

    public void startBattle(Player player, Enemy enemy) {
        battleView.displayBattleStart(player, enemy);

        while (player.getCurrentHp() > 0 && enemy.getCurrentHp() > 0) {
            player.incrementTurn();

            // Posibilidad de encontrar un ítem
            if (random.nextDouble() < 0.2) {
                Item item = itemController.generateRandomItem();
                battleView.displayItemFound(item);
                int useItem = scanner.nextInt();
                if (useItem == 1) {
                    item.use(player);
                }
            }

            // Menú de batalla
            battleView.displayBattleMenu(
                player, 
                player.isSpecialAttackAvailable(), 
                player.isSuperGuerreroAvailable()
            );
            
            performPlayerAction(player, enemy);

            if (enemy.getCurrentHp() <= 0) {
                battleView.displayBattleResult(true, player.getName(), enemy.getName());
                break;
            }

            // Turno del enemigo
            enemy.takeAction(player);
            player.resetStats();

            if (player.getCurrentHp() <= 0) {
                battleView.displayBattleResult(false, player.getName(), enemy.getName());
                break;
            }
        }

        if (player.getCurrentHp() > 0) {
            int pointsEarned = calculateBattlePoints(enemy);
            player.addPoints(pointsEarned);
            battleView.displayPointsEarned(pointsEarned);
        }
    }

    private int calculateBattlePoints(Enemy enemy) {
        return enemy.getMaxHp() + enemy.getAttack();
    }

    private void performPlayerAction(Player player, Enemy enemy) {
        while (true) {
            try {
                int action = scanner.nextInt();

                switch (action) {
                    case 1:
                        player.basicAttack(enemy);
                        break;
                    case 2:
                        if (player.isSpecialAttackAvailable()) {
                            player.specialAttack(enemy);
                        } else {
                            battleView.displayInvalidActionMessage();
                            continue;
                        }
                        break;
                    case 3:
                        player.defend();
                        break;
                    case 4:
                        if (player.isSuperGuerreroAvailable()) {
                            player.superGuerrero();
                        } else {
                            battleView.displayInvalidActionMessage();
                            continue;
                        }
                        break;
                    default:
                        battleView.displayInvalidActionMessage();
                        continue;
                }
                break;
            } catch (InputMismatchException e) {
                battleView.displayInvalidInputMessage();
                scanner.next();
            }
        }
    }
}