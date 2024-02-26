package kingdom.weapons.factories;

import kingdom.weapons.Sword;
import kingdom.weapons.Weapon;
import kingdom.weapons.enums.WeaponType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class CraftTest {

    @Test
    void createWeapon_shouldReturnCorrectWeapon() {
        WeaponType weaponType = WeaponType.SWORD;

        Weapon weapon = Craft.createWeapon(weaponType);

        Assertions.assertTrue(weapon instanceof Sword);
    }

    @Test
    void createWeapon_shouldThrowExceptionForInvalidWeaponType() {
        WeaponType weaponType = WeaponType.UNKNOWN;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Craft.createWeapon(weaponType);
        });
    }
}