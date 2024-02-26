package kingdom.units;

import kingdom.mediator.World;
import kingdom.units.behaviors.EatingBehavior;
import kingdom.units.behaviors.UnitBehavior;
import kingdom.units.enums.UnitType;

public interface Unit {
    String getName();
    Integer getHealth();
    Integer getMaxHealth();
    World getWorld();

    void setHealth(Integer maxHealth);

    void setBehavior(UnitBehavior behavior);

    Integer getHunger();

    UnitBehavior getBehavior();

    Integer getStrength();

    Integer getSpeed();

    Integer getSkill();

    UnitType getUnitType();
}
