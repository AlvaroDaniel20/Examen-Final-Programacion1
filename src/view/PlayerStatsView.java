package view;

import model.Player;

public class PlayerStatsView {
    public void displayPlayerStats(Player player) {
        System.out.println("\n--- ESTADISTICAS DEL JUGADOR ---");
        System.out.println("Nombre: " + player.getName());
        System.out.println("HP: " + player.getCurrentHp() + "/" + player.getMaxHp());
        System.out.println("Ataque: " + player.getAttack());
        System.out.println("Defense: " + player.getDefense());
        System.out.println("Ataque Especial: " + player.getSpecialAttack());
        System.out.println("Defensa Especial: " + player.getSpecialDefense());
        System.out.println();
        System.out.println("Puntos del jugador: " + player.getPoints());
    }
}