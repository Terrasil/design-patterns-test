package kingdom.enemies.factory;

import kingdom.composites.WolfPack;
import kingdom.enemies.Dragon;
import kingdom.enemies.Enemy;
import kingdom.enemies.enums.EventType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void createUnit_shouldReturnCorrectUnit() {
        EventType eventType = EventType.DRAGON;

        Enemy unit = Event.createUnit(eventType);

        Assertions.assertTrue(unit instanceof Dragon);
    }

    @Test
    void createUnit_shouldReturnWolfPackWithRandomNumberOfWolves() {
        EventType eventType = EventType.WOLF_PACK;

        WolfPack wolfPack = (WolfPack) Event.createUnit(eventType);

        Assertions.assertNotNull(wolfPack);
        Assertions.assertTrue(wolfPack.getWolves().size() > 0);
    }

    @Test
    void createUnit_shouldThrowExceptionForInvalidEventType() {
        EventType eventType = EventType.UNKNOWN;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Event.createUnit(eventType);
        });
    }
}