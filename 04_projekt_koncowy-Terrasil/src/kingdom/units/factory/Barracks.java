package kingdom.units.factory;

import kingdom.Kingdom;
import kingdom.units.*;
import kingdom.units.behaviors.EatingBehavior;
import kingdom.units.enums.UnitType;

import java.util.Random;

public class Barracks {
    private static final Random rng = new Random(System.currentTimeMillis());
    public static Human createUnit(UnitType unitType) {
        switch (unitType) {
            case HUMAN:
                Integer maxHealth = 50 + rng.nextInt(50);
                return new Human.Builder()
                        .hunger(100)
                        .maxHealth(maxHealth)
                        .health(maxHealth)
                        .strength(1 + rng.nextInt(10))
                        .speed(1 + rng.nextInt(10))
                        .skill(1 + rng.nextInt(10))
                        .behavior(new EatingBehavior())
                        .world(Kingdom.getInstance().getWorld())
                        .build();
            default:
                throw new IllegalArgumentException("Invalid unit type: " + unitType);
        }
    }
    public static Human upgradeUnit(UnitType unitType, Unit unit) {
        switch (unitType) {
            case BLACKSMITH:
                return new Blacksmith.Builder()
                        .hunger(unit.getHunger())
                        .health(unit.getHealth())
                        .strength(unit.getStrength())
                        .speed(unit.getSpeed())
                        .skill(unit.getSkill())
                        .maxHealth(unit.getMaxHealth())
                        .behavior(unit.getBehavior())
                        .world(Kingdom.getInstance().getWorld())
                        .build();
            case HUMAN:
                return new Human.Builder()
                        .hunger(unit.getHunger())
                        .health(unit.getHealth())
                        .strength(unit.getStrength())
                        .speed(unit.getSpeed())
                        .skill(unit.getSkill())
                        .maxHealth(unit.getMaxHealth())
                        .behavior(unit.getBehavior())
                        .world(Kingdom.getInstance().getWorld())
                        .build();
            case KNIGHT:
                return new Knight.Builder()
                        .hunger(unit.getHunger())
                        .health(unit.getHealth())
                        .strength(unit.getStrength())
                        .speed(unit.getSpeed())
                        .skill(unit.getSkill())
                        .maxHealth(unit.getMaxHealth())
                        .behavior(unit.getBehavior())
                        .world(Kingdom.getInstance().getWorld())
                        .build();
            case MINER:
                return new Miner.Builder()
                        .hunger(unit.getHunger())
                        .health(unit.getHealth())
                        .strength(unit.getStrength())
                        .speed(unit.getSpeed())
                        .skill(unit.getSkill())
                        .maxHealth(unit.getMaxHealth())
                        .behavior(unit.getBehavior())
                        .world(Kingdom.getInstance().getWorld())
                        .build();
            default:
                throw new IllegalArgumentException("Invalid unit type: " + unitType);
        }
    }
}
