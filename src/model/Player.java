package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Player extends Character {
    private static final int BASE_HP = 100;
    private static final int BASE_ATTACK = 35;
    private static final int BASE_DEFENSE = 15;
    private static final int BASE_SPECIAL_ATTACK = 45;
    private static final int BASE_SPECIAL_DEFENSE = 30;
    private int points = 0;
    private int original_attack;
    private int original_defense;
    private boolean isSuperGuerreroUsed;

    private int turns;
    private Queue<String> actionHistory = new LinkedList<>();
    
    public Player(String name) {
        super(name, BASE_HP, BASE_ATTACK, BASE_DEFENSE, BASE_SPECIAL_ATTACK, BASE_SPECIAL_DEFENSE);
        this.turns = 0;
        this.original_attack = BASE_ATTACK;
        this.original_defense = BASE_DEFENSE;
        this.isSuperGuerreroUsed = false;
    }

    public void incrementTurn() {
        this.turns++;
    }

    public int getTurns() {
        return this.turns;
    }

    public boolean isSpecialAttackAvailable() {
        if (this.turns >= 2) {
            Random random = new Random();
            return random.nextDouble() < 0.3;
        }
        return false;
    }

    public void basicAttack(Character target) {
        int damage = calculateDamage(this.attack, target.getDefense());
        target.takeDamage(damage);
        recordAction("attack");
        System.out.println(this.name + " realiza un ataque basico y causa: " + damage + " puntos de daño!");
    }
    
    public void specialAttack(Character target) {
        int damage = calculateDamage(this.specialAttack, target.getDefense());
        target.takeDamage(damage);
        recordAction("specialAttack");
        System.out.println(this.name + " realiza un ataque especial y causa: " + damage + " puntos de daño!");
    }

    public void defend() {
        recordAction("defense");
        System.out.println(this.name + " adquiere una postura defensiva!");
        this.defense += 8;
    }

    public void boostAttack(int amount) {
        this.attack += amount;
        System.out.println(this.name + " ahora tiene " + this.attack + " puntos de ataque.");
    }

    public void boostDefense(int amount) {
        this.defense += amount;
        System.out.println(this.name + " ahora tiene " + this.defense + " puntos de defensa.");
    }

    public void superGuerrero() {
        if (!isSuperGuerreroUsed) {
            this.attack += this.attack * 0.25;
            this.defense += this.defense * 0.20;

            this.maxHp += this.maxHp * 0.15;
            int healAmount = (int) (this.currentHp * 0.20);
            this.heal(healAmount);
            System.out.println(this.name + " se ha transformado en Super Guerrero");

            isSuperGuerreroUsed = true;
        }
    }

    public boolean isSuperGuerreroAvailable() {
        if (this.turns >= 5 && !isSuperGuerreroUsed) {
            Random random = new Random();
            return random.nextDouble() < 0.4;
        }
        return false;
    }

    private void recordAction(String action) {
        if (actionHistory.size() >= 3) {
            actionHistory.poll();
        }
        actionHistory.add(action);
    }

    public Queue<String> getActionHistory() {
        return new LinkedList<>(actionHistory);
    }

    private int calculateDamage(int attackPower, int targetDefense) {
        double attackRandom = getRandomFactor();
        return (int) Math.max(1, (attackPower * attackRandom - targetDefense * 0.5));
    }

    public void resetStats() {
        this.attack = original_attack;
        this.defense = original_defense;
    }

    // Metodos para sistema de puntos

    public void addPoints(int pointsToAdd) {
        this.points += pointsToAdd;
    }

    public int getPoints() {
        return this.points;
    }

    public void resetPoints() {
        this.points = 0;
    }
}