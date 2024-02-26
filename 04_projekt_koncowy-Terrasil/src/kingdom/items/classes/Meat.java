package kingdom.items.classes;

import kingdom.items.interfaces.Item;

public class Meat implements Item {
    public static final Integer HEAL_VALUE = 10;
    private String name = "Mięso";

    @Override
    public String getName() {
        return name;
    }
}
