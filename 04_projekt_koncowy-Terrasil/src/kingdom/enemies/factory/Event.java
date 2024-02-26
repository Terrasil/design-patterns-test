package kingdom.enemies.factory;

import kingdom.composites.WolfPack;
import kingdom.enemies.Dragon;
import kingdom.enemies.Enemy;
import kingdom.enemies.IronOreVein;
import kingdom.enemies.Wolf;
import kingdom.enemies.enums.EventType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Event {
    private static Random rng = new Random(System.currentTimeMillis());
    public static Enemy createUnit(EventType eventType) {
        switch (eventType) {
            case DRAGON:
                return new Dragon.Builder().build();
            case IRON_ORE_VEIN:
                return new IronOreVein.Builder().build();
            case WOLF:
                return new Wolf.Builder().build();
            case WOLF_PACK:
                Integer wolfCount = rng.nextInt(8);
                List<Wolf> wolves = new ArrayList<>();
                for(Integer w = 0; w < wolfCount; w++){
                    wolves.add(new Wolf.Builder().build());
                }
                return new WolfPack.Builder().wolves(wolves).build();
            default:
                throw new IllegalArgumentException("Invalid event type: " + eventType);
        }
    }
}
