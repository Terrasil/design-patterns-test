package kingdom.units;

import kingdom.mediator.World;
import kingdom.units.behaviors.UnitBehavior;
import kingdom.units.enums.UnitType;
import kingdom.weapons.Weapon;

public class Knight extends Human implements Unit {
    private String name = "Rycerz";
    private UnitType unitType = UnitType.KNIGHT;
    private Weapon weapon;
    private Knight(Builder builder) {
        this.strength = builder.strength;
        this.speed = builder.speed;
        this.skill = builder.skill;
        this.health = builder.health;
        this.maxHealth = builder.maxHealth;
        this.hunger = builder.hunger;
        this.world = builder.world;
        this.behavior = builder.behavior;
        this.weapon = builder.weapon;
    }

    public Knight() {
    }

    @Override
    public String getName(){
        return this.name;
    }
    @Override
    public UnitType getUnitType() {
        return this.unitType;
    }
    public Weapon getWeapon(){
        return this.weapon;
    }
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
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
        private Weapon weapon;

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

        public Builder world(World world) {
            this.world = world;
            return this;
        }

        public Builder behavior(UnitBehavior behavior) {
            this.behavior = behavior;
            return this;
        }

        public Builder weapon(Weapon weapon) {
            this.weapon = weapon;
            return this;
        }

        public Knight build() {
            return new Knight(this);
        }
    }
}
