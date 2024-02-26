package kingdom.weapons.factories;

import kingdom.weapons.Axe;
import kingdom.weapons.Hammer;
import kingdom.weapons.Sword;
import kingdom.weapons.Weapon;
import kingdom.weapons.enums.WeaponType;

public class Craft {
    public static Weapon createWeapon(WeaponType weaponType) {
        switch (weaponType) {
            case AXE:
                return new Axe.Builder().build();
            case HAMMER:
                return new Hammer.Builder().build();
            case SWORD:
                return new Sword.Builder().build();
            default:
                throw new IllegalArgumentException("Invalid weapon type: " + weaponType);
        }
    }
}
