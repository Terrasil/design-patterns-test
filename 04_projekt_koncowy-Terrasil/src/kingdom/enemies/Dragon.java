package kingdom.enemies;

import kingdom.Kingdom;
import kingdom.items.classes.Meat;
import kingdom.items.interfaces.Item;

import java.util.Random;

public class Dragon implements Enemy {
    private static String name = "Smok";
    private Random rng = new Random(System.currentTimeMillis());
    private Integer health = 100;

    @Override
    public void drop() {
        Integer amount = 5 + rng.nextInt(5);
        Item item = new Meat();
        System.out.format("[?] Pokonano %s i otrzymano %d %s%n", name, amount, item.getName());
        for(Integer i = 0; i < amount; i++){
            Item _item = new Meat();
            Kingdom.getInstance().getWarehouse().add(_item);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void takeDamage(Integer damage) {
        this.health -= damage / 2;
    }

    @Override
    public Integer getHealth() {
        return this.health;
    }

    private Dragon(Builder builder) {
    }

    public static class Builder {

        public Dragon build() {
            return new Dragon(this);
        }
    }
}
