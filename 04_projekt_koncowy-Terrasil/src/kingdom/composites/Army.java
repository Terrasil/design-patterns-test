package kingdom.composites;

import kingdom.units.Knight;
import kingdom.units.Unit;

import java.util.List;

public class Army extends Knight implements Unit {
    private String name = "Armia";
    private List<Knight> knights;

    public List<Knight> getKnights(){
        return this.knights;
    }

    @Override
    public String getName(){
        return this.name + " (" + knights.size() + ")";
    }

    private Army(Builder builder) {
        this.knights = builder.knights;
    }
    public static class Builder {
        private List<Knight> knights;

        public Builder knights(List<Knight> knights) {
            this.knights = knights;
            return this;
        }

        public Army build() {
            return new Army(this);
        }
    }
}
