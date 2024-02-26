package kingdom.units;

import kingdom.mediator.World;
import kingdom.units.behaviors.EatingBehavior;
import kingdom.units.behaviors.UnitBehavior;
import kingdom.units.enums.UnitType;

public class Human implements Unit {
    private String name = "Wolny mieszkaniec";
    private UnitType unitType = UnitType.HUMAN;
    World world;
    Integer strength;
    Integer skill;
    Integer speed;
    Integer health;
    Integer maxHealth;
    Integer hunger;
    UnitBehavior behavior;

    Human(){
    }

    private Human(Builder builder) {
        this.strength = builder.strength;
        this.speed = builder.speed;
        this.skill = builder.skill;
        this.health = builder.health;
        this.maxHealth = builder.maxHealth;
        this.hunger = builder.hunger;
        this.world = builder.world;
        this.behavior = builder.behavior;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public UnitType getUnitType() {
        return this.unitType;
    }

    @Override
    public Integer getHealth() {
        return health;
    }

    @Override
    public Integer getMaxHealth() {
        return maxHealth;
    }

    @Override
    public void setHealth(Integer health) {
        this.health = health;
    }

    @Override
    public void setBehavior(UnitBehavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public Integer getHunger() {
        return this.hunger;
    }

    @Override
    public UnitBehavior getBehavior() {
        return this.behavior;
    }

    @Override
    public Integer getStrength() {
        return this.strength;
    }

    @Override
    public Integer getSpeed() {
        return this.speed;
    }

    @Override
    public Integer getSkill() {
        return this.skill;
    }

    @Override
    public World getWorld() {
        return world;
    }

    public void performBehavior() {
        this.hunger--;
        this.world.interact(this);
        behavior.performBehavior(this);
    }

    public static class Builder {
        private Integer strength;
        private Integer speed;
        private Integer skill;
        private Integer health;
        private Integer maxHealth;
        private Integer hunger;
        private World world;
        private UnitBehavior behavior;

        public Builder strength(Integer strength) {
            this.strength = strength;
            return this;
        }

        public Builder speed(Integer speed) {
            this.speed = speed;
            return this;
        }

        public Builder skill(Integer skill) {
            this.skill = skill;
            return this;
        }

        public Builder health(Integer health) {
            this.health = health;
            return this;
        }

        public Builder maxHealth(Integer maxHealth) {
            this.maxHealth = maxHealth;
            return this;
        }

        public Builder hunger(Integer hunger) {
            this.hunger = hunger;
            return this;
        }

        public Builder behavior(UnitBehavior behavior) {
            this.behavior = behavior;
            return this;
        }

        public Builder world(World world) {
            this.world = world;
            return this;
        }

        public Human build() {
            return new Human(this);
        }
    }
}
