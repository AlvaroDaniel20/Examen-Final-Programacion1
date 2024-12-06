package model;

public class Item {
    private String name;
    private String type;
    private int potency;

    public Item(String name, String type, int potency) {
        this.name = name;
        this.type = type;
        this.potency = potency;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPotency() {
        return potency;
    }

    public void use(Player player) {
        switch (type) {
            case "attack":
                player.boostAttack(potency);
                System.out.println(player.getName() + " ha usado " + name + " y su ataque a aumentado en " + potency + " puntos!");
                break;
            case "defense":
                player.boostDefense(potency);
                System.out.println(player.getName() + " ha usado " + name + " y su defensa a aumentado en " + potency + " puntos!");
                break;
            case "heal":
                player.heal(potency);
                System.out.println(player.getName() + " ha usado " + name + " y ha recuperado " + potency + " HP!");
                break;
            default:
                break;
        }
    }
}