package controller;

import model.Item;
import java.util.Random;

public class ItemController {
    private Random random;

    public ItemController() {
        this.random = new Random();
    }

    public Item generateRandomItem() {
        int itemType = random.nextInt(3);
        switch (itemType) {
            case 0:
                return new Item("Potenciador de ataque", "attack", 10 + random.nextInt(11));
            case 1:
                return new Item("Escudo reforzado", "defense", 5 + random.nextInt(6));
            case 2:
                return new Item("Poción de salud", "heal", random.nextInt(21));
            default:
                return null;
        }
    }
}