package kingdom.items.classes;

import kingdom.items.interfaces.Item;

public class IronOre implements Item {
    private String name = "Ruda żelaza";

    @Override
    public String getName() {
        return name;
    }
}
