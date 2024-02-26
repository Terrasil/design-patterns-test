package kingdom.weapons;

import kingdom.items.interfaces.Item;

import java.util.List;

public class Sword implements Weapon {
    private String name = "Miecz";
    private Integer damage = 5;
    private Integer cost = 1;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getCost() {
        return this.cost;
    }

    @Override
    public Integer getDamage() {
        return this.damage;
    }

    private Sword(Builder builder) {
        this.name = builder.name;
        this.damage = builder.damage;
        this.cost = builder.cost;
    }

    public static class Builder {
        private String name = "Miecz";
        private Integer damage = 5;
        private Integer cost = 1;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder damage(Integer damage) {
            this.damage = damage;
            return this;
        }

        public Builder cost(Integer skill) {
            this.cost = cost;
            return this;
        }

        public Sword build() {
            return new Sword(this);
        }
    }
}
