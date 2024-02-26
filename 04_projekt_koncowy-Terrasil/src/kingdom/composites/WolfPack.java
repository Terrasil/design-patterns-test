package kingdom.composites;

import kingdom.enemies.Enemy;
import kingdom.enemies.Wolf;

import java.util.List;

public class WolfPack extends Wolf implements Enemy {
    private static String name = "Watacha wilków";

    @Override
    public String getName() {
        return this.name;
    }
    private List<Wolf> wolves;

    private WolfPack(Builder builder) {
        this.wolves = builder.wolves;
    }

    public List<Wolf> getWolves() {
        return this.wolves;
    }

    public static class Builder {
        private List<Wolf> wolves;

        public Builder wolves(List<Wolf> wolves) {
            this.wolves = wolves;
            return this;
        }

        public WolfPack build() {
            return new WolfPack(this);
        }
    }
}
