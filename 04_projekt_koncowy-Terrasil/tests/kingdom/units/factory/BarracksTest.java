package kingdom.units.factory;
import kingdom.mediator.World;
import kingdom.units.Human;
import kingdom.units.Knight;
import kingdom.units.Unit;
import kingdom.units.behaviors.UnitBehavior;
import kingdom.units.enums.UnitType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

class BarracksTest {

    @Test
    void createUnit_shouldReturnHumanWithRandomAttributes() {
        Random rng = new Random();
        UnitType unitType = UnitType.HUMAN;

        Human unit = Barracks.createUnit(unitType);

        Assertions.assertEquals(unitType, unit.getUnitType());
        Assertions.assertEquals(100, unit.getHunger());
        Assertions.assertTrue(unit.getMaxHealth() >= 50 && unit.getMaxHealth() <= 100);
        Assertions.assertEquals(unit.getMaxHealth(), unit.getHealth());
        Assertions.assertTrue(unit.getStrength() >= 1 && unit.getStrength() <= 10);
        Assertions.assertTrue(unit.getSpeed() >= 1 && unit.getSpeed() <= 10);
        Assertions.assertTrue(unit.getSkill() >= 1 && unit.getSkill() <= 10);
        Assertions.assertNotNull(unit.getBehavior());
        Assertions.assertNotNull(unit.getWorld());
    }

    @Test
    void upgradeUnit_shouldReturnCorrectUpgradedUnit() {
        UnitType unitType = UnitType.KNIGHT;
        Unit human = Barracks.createUnit(UnitType.HUMAN);
        Unit unit = Barracks.upgradeUnit(UnitType.KNIGHT, human);
        Human upgradedUnit = Barracks.upgradeUnit(unitType, unit);

        Assertions.assertTrue(upgradedUnit instanceof Knight);
        Assertions.assertEquals(unit.getHunger(), upgradedUnit.getHunger());
        Assertions.assertEquals(unit.getHealth(), upgradedUnit.getHealth());
        Assertions.assertEquals(unit.getStrength(), upgradedUnit.getStrength());
        Assertions.assertEquals(unit.getSpeed(), upgradedUnit.getSpeed());
        Assertions.assertEquals(unit.getSkill(), upgradedUnit.getSkill());
        Assertions.assertEquals(unit.getMaxHealth(), upgradedUnit.getMaxHealth());
        Assertions.assertEquals(unit.getBehavior(), upgradedUnit.getBehavior());
        Assertions.assertEquals(unit.getWorld(), upgradedUnit.getWorld());
    }
}