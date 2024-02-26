package kingdom.units.behaviors;

import kingdom.Kingdom;
import kingdom.enemies.Enemy;
import kingdom.items.classes.Meat;
import kingdom.items.interfaces.Item;
import kingdom.units.Unit;

import java.util.List;

public class EatingBehavior implements UnitBehavior{
    @Override
    public void performBehavior(Unit target) {
        // Tylko dla Meat
        Integer needToEat = (target.getMaxHealth() - target.getHealth()) % Meat.HEAL_VALUE;
        List<Item> kingdomWarehouse = Kingdom.getInstance().getWarehouse();
        for(Integer m = 0; m < needToEat; m++) {
            Item meat = kingdomWarehouse.stream().filter(i -> i instanceof Meat).findFirst().get();
            if(meat != null){
                target.setHealth(target.getHealth() + 10);
                kingdomWarehouse.remove(meat);
                if(target.getHealth() > target.getMaxHealth()) {
                    target.setHealth(target.getMaxHealth());
                    break;
                }
            }
        }
        Kingdom.getInstance().setWarehouse(kingdomWarehouse);
    }

    @Override
    public void performBehaviorOn(Unit target, Enemy enemy) {
    }
}