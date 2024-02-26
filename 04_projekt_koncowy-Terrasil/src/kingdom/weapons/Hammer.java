package kingdom.weapons;

public class Hammer implements Weapon {
    private String name = "Młot";
    private Integer damage = 15;
    private Integer cost = 3;
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

    private Hammer(Builder builder) {
        this.name = builder.name;
        this.damage = builder.damage;
        this.cost = builder.cost;
    }
    public static class Builder {
        private String name = "Młot";
        private Integer damage = 15;
        private Integer cost = 3;

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

        public Hammer build() {
            return new Hammer(this);
        }
    }
}
