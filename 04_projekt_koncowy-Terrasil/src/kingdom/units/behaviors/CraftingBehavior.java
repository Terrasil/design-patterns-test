package kingdom.units.behaviors;

import kingdom.Kingdom;
import kingdom.composites.Army;
import kingdom.enemies.Enemy;
import kingdom.items.classes.IronIngot;
import kingdom.items.classes.IronOre;
import kingdom.items.interfaces.Item;
import kingdom.units.Blacksmith;
import kingdom.units.Human;
import kingdom.units.Knight;
import kingdom.units.Unit;
import kingdom.weapons.Weapon;
import kingdom.weapons.enums.WeaponType;
import kingdom.weapons.factories.Craft;

import java.util.List;

public class CraftingBehavior implements UnitBehavior{
    @Override
    public void performBehavior(Unit target) {
        if(target instanceof Blacksmith){
            List<Item> kingdomWarehouse = Kingdom.getInstance().getWarehouse();
            List<Weapon> kingdomArmory = Kingdom.getInstance().getArmory();

            // Wyliczanie aktywnych rycerzy
            Long knights = Kingdom.getInstance().getCitizens().stream().filter(i -> i instanceof Knight).count();
            List<Human> armies = Kingdom.getInstance().getCitizens().stream().filter(i -> i instanceof Army).toList();
            for(Human human : armies){
                Army army = (Army) human;
                knights += army.getKnights().size();
            }
            List<Item> ingots = kingdomWarehouse.stream().filter(i -> i instanceof IronIngot).toList();
            if(ingots.size() == 0) {
                // wytwarzanie sztabek
                List<Item> ores = kingdomWarehouse.stream().filter(io -> io instanceof IronOre).toList();
                if(ores.size() > 0) {
                    kingdomWarehouse.remove(ores.get(0));
                    kingdomWarehouse.add(new IronIngot());
                    Kingdom.getInstance().setWarehouse(kingdomWarehouse);
                }
            }else{
                // ustalanie typu
                WeaponType weaponToCraft = WeaponType.SWORD;
                if(ingots.size() > (knights/2)) weaponToCraft = WeaponType.AXE;
                if(ingots.size() > knights) weaponToCraft = WeaponType.HAMMER;

                // weryfikacja wymaganych zasobów
                Weapon newWeapon = Craft.createWeapon(weaponToCraft);
                while(ingots.size() < newWeapon.getCost()) {
                    weaponToCraft = WeaponType.lesserType(weaponToCraft);
                    newWeapon = Craft.createWeapon(weaponToCraft);
                }

                //pobranie materiałow
                for(Integer m = 0; m < newWeapon.getCost(); m++) {
                    if(ingots.size() > 0) kingdomWarehouse.remove(ingots.get(0));
                }

                //umieszczenie nowej broni w zbrojowni
                kingdomArmory.add(newWeapon);
                Kingdom.getInstance().setArmory(kingdomArmory);
            }
        }
    }

    @Override
    public void performBehaviorOn(Unit target, Enemy enemy) {
    }
}
