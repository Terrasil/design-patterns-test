package kingdom.units.behaviors;

import kingdom.composites.WolfPack;
import kingdom.enemies.Enemy;
import kingdom.enemies.IronOreVein;
import kingdom.mediator.World;
import kingdom.units.Knight;
import kingdom.units.Unit;

import java.util.List;
import java.util.Random;

public class AttackBehavior implements UnitBehavior{
    private Random rng = new Random(System.currentTimeMillis());
    @Override
    public void performBehavior(Unit target) {
        if(target instanceof Knight){
            Knight knight = (Knight) target;
            World  world = target.getWorld();
            List<Enemy> enemies = world.getEnemies().stream().filter(e -> !(e instanceof IronOreVein)).toList();
            if(!enemies.isEmpty()){
                Enemy randomEnemy = enemies.get(rng.nextInt(enemies.size()));
                if(randomEnemy instanceof WolfPack){
                    WolfPack wolfPack = (WolfPack) randomEnemy;
                    if(wolfPack.getWolves().isEmpty()){
                        world.getEnemies().remove(randomEnemy);
                    }else{
                        randomEnemy = wolfPack.getWolves().get(rng.nextInt(wolfPack.getWolves().size()));
                    }
                    randomEnemy.takeDamage(knight.getStrength() + (knight.getWeapon() != null ? knight.getWeapon().getDamage() : 0));
                    if(randomEnemy.getHealth() <= 0){
                        randomEnemy.drop();
                        wolfPack.getWolves().remove(randomEnemy);
                    }
                }
                else{
                    randomEnemy.takeDamage(knight.getStrength() + (knight.getWeapon() != null ? knight.getWeapon().getDamage() : 0));
                    if(randomEnemy.getHealth() <= 0){
                        randomEnemy.drop();
                        world.getEnemies().remove(randomEnemy);
                    }
                }
            }
        }
    }

    @Override
    public void performBehaviorOn(Unit target, Enemy enemy) {
        World  world = target.getWorld();
        Knight knight = (Knight) target;
        if(enemy instanceof WolfPack){
            WolfPack wolfPack = (WolfPack) enemy;
            if(wolfPack.getWolves().isEmpty()){
                world.getEnemies().remove(enemy);
            }else{
                enemy = wolfPack.getWolves().get(rng.nextInt(wolfPack.getWolves().size()));
            }
            enemy.takeDamage(knight.getStrength() + (knight.getWeapon() != null ? knight.getWeapon().getDamage() : 0));
            if(enemy.getHealth() <= 0){
                enemy.drop();
                wolfPack.getWolves().remove(enemy);
            }
        }
        else{
            enemy.takeDamage(knight.getStrength() + (knight.getWeapon() != null ? knight.getWeapon().getDamage() : 0));
            if(enemy.getHealth() <= 0){
                enemy.drop();
                world.getEnemies().remove(enemy);
            }
        }
    }
}
