package model;

import java.util.Queue;

public class Enemy extends Character {
    private static final int BASE_HP = 120;
    private static final int BASE_ATTACK = 35;
    private static final int BASE_DEFENSE = 20;
    private static final int BASE_SPECIAL_ATTACK = 40;
    private static final int BASE_SPECIAL_DEFENSE = 30;

    private int consecutiveBasicAttacks = 0;
    private int consecutiveDefenses = 0;
    private int consecutiveDebuff = 0;
    private static final int MAX_CONSECUTIVES_ACTIONS = 2;

    public Enemy(String name) {
        super(name, BASE_HP, BASE_ATTACK, BASE_DEFENSE, BASE_SPECIAL_ATTACK, BASE_SPECIAL_DEFENSE); 
    }

    public void takeAction(Player player) {
        Queue<String> playerActions = player.getActionHistory();
        boolean playerIsAggressive = playerActions.stream().allMatch(action -> action.equals("attack"));

        if (playerIsAggressive) {
            applyAttackDebuff(player);
        } else if (this.currentHp < this.maxHp * 0.3 && Math.random() < 0.5) {
            healAction();
        } else if (player.getCurrentHp() < player.getMaxHp() * 0.3 && Math.random() < 0.5) {
            specialAttack(player);
        } else if (this.currentHp < this.maxHp * 0.5 && Math.random() < 0.5) {
            defend(player);
        } else {
            basicAttack(player);
        }
    }

    public void applyAttackDebuff(Player player) {
        if (consecutiveDebuff < MAX_CONSECUTIVES_ACTIONS) {
            player.attack -= 5;
            System.out.println(this.name + " usa un hechizo debilitador y reduce el ataque de " + player.getName() + " en 5 puntos!");
            consecutiveDebuff++;
            consecutiveBasicAttacks = 0;
            consecutiveDefenses = 0;
        } else {
            basicAttack(player);
        }
    }

    public void basicAttack(Player player) {
        if (consecutiveBasicAttacks < MAX_CONSECUTIVES_ACTIONS) {
            int damage = calculateDamage(this.attack, player.getDefense());
            player.takeDamage(damage);
            System.out.println(this.name + " realiza un ataque basico y causa: " + damage + " puntos de daño!");
            consecutiveBasicAttacks++;
            consecutiveDefenses = 0;
        } else {
            defend(player);
        }
    }

    public void specialAttack(Player player) {
        int damage = calculateDamage(this.specialAttack, player.getSpecialDefense());
        player.takeDamage(damage);
        System.out.println(this.name + " usa su habilidad especial y causa: " + damage + " puntos de daño");
        consecutiveBasicAttacks = 0;
        consecutiveDefenses = 0;
    }

    public void defend(Player player) {
        if (consecutiveDefenses < MAX_CONSECUTIVES_ACTIONS) {
            System.out.println(this.name + " adquiere una postura defensiva!");
            this.defense += 8;
            consecutiveDefenses++;
            consecutiveBasicAttacks = 0;
        } else {
            basicAttack(player);
        }
    }

    public void healAction() {
        int healAmount = (int) (this.maxHp * 0.25);
        this.heal(healAmount);
        System.out.println(this.name + " usa una pocion y recupera " + healAmount + " HP!");
        consecutiveBasicAttacks = 0;
        consecutiveDefenses = 0;
    }

    private int calculateDamage(int attackPower, int targetDefense) {
        double attackRandom = getRandomFactor();
        return (int) Math.max(1, (attackPower * attackRandom - targetDefense * 0.5));
    }
}