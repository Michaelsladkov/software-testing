package task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BottleTest {
    private static final String BOTTLE_NAME = "Bottle under test";
    private static final String DEFAULT_BOTTLE_NAME = "a bottle";

    @Test
    public void defaultNameTest() {
        Bottle bottle = new Bottle();
        Assertions.assertEquals(bottle.getName(), DEFAULT_BOTTLE_NAME);
    }

    @Test
    public void nameSettingTest() {
        Bottle bottle = new Bottle(BOTTLE_NAME);
        Assertions.assertEquals(bottle.getName(), BOTTLE_NAME);
    }

    @Test
    public void itemSettingTest() {
        Bottle bottle = new Bottle(BOTTLE_NAME);
        Takeable fish = new LittleYellowFish();
        Assertions.assertNull(bottle.getContents());
        bottle.fillWith(fish);
        Assertions.assertEquals(bottle.getContents(), fish);
    }

    @Test
    public void itemExtractionTest() {
        Bottle bottle = new Bottle(BOTTLE_NAME);
        Takeable fish = new LittleYellowFish();
        bottle.fillWith(fish);
        Assertions.assertEquals(bottle.getContents(), fish);
        Takeable extracted;
        Assertions.assertEquals(extracted = bottle.extract(), fish);
        Assertions.assertTrue(extracted == fish);
        Assertions.assertNull(bottle.getContents());
    }
}
