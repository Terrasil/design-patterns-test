package kingdom.enemies;

import kingdom.Kingdom;
import kingdom.items.classes.Meat;
import kingdom.items.interfaces.Item;

public class Wolf implements Enemy {
    private static String name = "Wilk";
    private Integer health = 20;

    public Wolf() {
    }

    @Override
    public void drop() {
        Integer amount = 1;
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
        this.health -= damage;
    }

    private Wolf(Builder builder) {
    }

    @Override
    public Integer getHealth() {
        return this.health;
    }

    public static class Builder {

        public Wolf build() {
            return new Wolf(this);
        }
    }
}