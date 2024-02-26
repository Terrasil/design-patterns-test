package kingdom.mediator;

import kingdom.enemies.Enemy;
import kingdom.enemies.IronOreVein;
import kingdom.units.Knight;
import kingdom.units.Miner;
import kingdom.units.Unit;
import kingdom.units.behaviors.AttackBehavior;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    private List<Enemy> enemies = new ArrayList<>();
    private Random rng = new Random(System.currentTimeMillis());

    public void setNewEvent(Enemy enemy){
        System.out.format("[!] Pojawił/a/y się %s%n", enemy.getName());
        this.enemies.add(enemy);
    }

    public void interact(Unit unit){
        Enemy enemyToPerform = null;
        if (Knight.class.getName().equals(unit.getClass())) {
            unit.setBehavior(new AttackBehavior());
            if(!enemies.isEmpty()){
                List<Enemy> filteredEnemies = enemies.stream().filter(e -> !(e instanceof IronOreVein)).toList();
                enemyToPerform = filteredEnemies.get(rng.nextInt(filteredEnemies.size()));
            }
            unit.getBehavior().performBehaviorOn(unit, enemyToPerform);
        }
        else if (Miner.class.getName().equals(unit.getClass())) {
            unit.setBehavior(new AttackBehavior());
            if(!enemies.isEmpty()){
                List<Enemy> filteredEnemies = enemies.stream().filter(e -> e instanceof IronOreVein).toList();
                enemyToPerform = filteredEnemies.get(rng.nextInt(filteredEnemies.size()));
            }
            unit.getBehavior().performBehaviorOn(unit, enemyToPerform);
        }
    }

    public List<Enemy> getEnemies() {
        return this.enemies;
    }
}
