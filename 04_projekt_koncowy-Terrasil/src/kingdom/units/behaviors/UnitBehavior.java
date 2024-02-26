package kingdom.units.behaviors;

import kingdom.enemies.Enemy;
import kingdom.units.Unit;

public interface UnitBehavior {
    void performBehavior(Unit target);
    void performBehaviorOn(Unit target, Enemy enemy);
}
