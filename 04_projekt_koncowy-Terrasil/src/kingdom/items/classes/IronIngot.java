package kingdom.items.classes;

import kingdom.items.interfaces.Item;

public class IronIngot implements Item {
    private String name = "Żelazna sztabka";

    @Override
    public String getName() {
        return name;
    }
}
