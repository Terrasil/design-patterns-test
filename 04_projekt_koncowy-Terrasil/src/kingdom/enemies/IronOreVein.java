package kingdom.enemies;

import kingdom.Kingdom;
import kingdom.items.classes.IronOre;
import kingdom.items.classes.Meat;
import kingdom.items.interfaces.Item;

import java.util.Random;

public class IronOreVein implements Enemy {
    private static String name = "Żyła żelaza";

    private Random rng = new Random(System.currentTimeMillis());
    private Integer health = 3;

    @Override
    public void drop() {
        Integer amount = rng.nextInt(5);
        Item item = new IronOre();
        System.out.format("[?] Pokonano %s i otrzymano %d %s%n", name, amount, item.getName());
        for(Integer i = 0; i < amount; i++){
            Item _item = new IronOre();
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

    private IronOreVein(Builder builder) {
    }

    public static class Builder {

        public IronOreVein build() {
            return new IronOreVein(this);
        }
    }
}