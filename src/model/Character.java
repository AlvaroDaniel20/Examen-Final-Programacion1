package model;

public abstract class Character {
    protected String name;
    protected int maxHp;
    protected int currentHp;
    protected int attack;
    protected int defense;
    protected int specialAttack;
    protected int specialDefense;

    public Character(String name, int maxHp, int attack, int defense, int specialAttack, int specialDefense){
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
    }
    
    public String getName() {return this.name;}
    public int getMaxHp() {return this.maxHp;}
    public int getCurrentHp() {return this.currentHp;}
    public int getAttack() {return this.attack;}
    public int getDefense() {return this.defense;}
    public int getSpecialAttack() {return this.specialAttack;}
    public int getSpecialDefense() {return this.specialDefense;}

    public void takeDamage(int damage) {
        int reducedDamage = applyDefense(damage);
        this.currentHp = Math.max(0, this.currentHp - reducedDamage);
        System.out.println(this.name + " recibe " + damage + " puntos de daño!");
        System.out.println("HP actual: " + this.currentHp + "/" + this.maxHp);
    }

    public void heal(int amount) {
        int healedAmount = Math.min(amount, maxHp - currentHp);
        this.currentHp += healedAmount;
        System.out.println(this.name + " se ha curado: " + healedAmount + " HP!");
        System.out.println("HP actual: " + this.currentHp + "/" + this.maxHp);
    }

    private int applyDefense(int damage) {
        double defenseRandom = getRandomFactor();
        int effectiveDefense = (int) (this.defense * defenseRandom);
        int reducedDamage = (int) Math.max(1, damage -  effectiveDefense * 0.5);
        return reducedDamage;
    }

    protected double getRandomFactor() {
        return 0.85 + Math.random() * 0.15;
    }
}
