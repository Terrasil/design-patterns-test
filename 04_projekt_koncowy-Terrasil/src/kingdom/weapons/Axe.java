package kingdom.weapons;

public class Axe implements Weapon {
    private String name = "Topór";
    private Integer damage = 10;
    private Integer cost = 2;
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

    private Axe(Builder builder) {
        this.name = builder.name;
        this.damage = builder.damage;
        this.cost = builder.cost;
    }
    public static class Builder {
        private String name = "Topór";
        private Integer damage = 10;
        private Integer cost = 2;

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

        public Axe build() {
            return new Axe(this);
        }
    }
}
