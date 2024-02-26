package kingdom.enemies;

public interface Enemy{
    void drop();

    String getName();
    void takeDamage(Integer damage);

    Integer getHealth();
}
