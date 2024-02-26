package kingdom.units.behaviors;

import kingdom.enemies.Enemy;
import kingdom.enemies.IronOreVein;
import kingdom.mediator.World;
import kingdom.units.Miner;
import kingdom.units.Unit;

import java.util.List;
import java.util.Random;

public class MiningBehavior implements UnitBehavior{
    private Random rng = new Random(System.currentTimeMillis());
    @Override
    public void performBehavior(Unit target) {
        if(target instanceof Miner){
            World world = target.getWorld();
            List<Enemy> enemies = world.getEnemies().stream().filter(e -> e instanceof IronOreVein).toList();
            if(!enemies.isEmpty()){
                IronOreVein ironOreVein = (IronOreVein) enemies.get(rng.nextInt(enemies.size()));
                ironOreVein.takeDamage(target.getSpeed());
                if(ironOreVein.getHealth() <= 0){
                    world.getEnemies().remove(ironOreVein);
                }
            }
        }
    }

    @Override
    public void performBehaviorOn(Unit target, Enemy enemy) {
        World world = target.getWorld();
        enemy.takeDamage(target.getSpeed());
        if(enemy.getHealth() <= 0){
            world.getEnemies().remove(enemy);
        }
    }
}
