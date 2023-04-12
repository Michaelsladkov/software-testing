package task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LittleYellowFishTest {
    private static final String FISH_NAME = "Fish";
    private static final String DEFAULT_FISH_NAME = "some little yellow fish";

    @Test
    public void defaultNameTest() {
        LittleYellowFish fish = new LittleYellowFish();
        Assertions.assertEquals(fish.getName(), DEFAULT_FISH_NAME);
    }

    @Test
    public void nameSettingTest() {
        LittleYellowFish fish = new LittleYellowFish(FISH_NAME);
        Assertions.assertEquals(fish.getName(), FISH_NAME);
    }

    @Test
    public void translationTest() {
        LittleYellowFish fish = new LittleYellowFish(FISH_NAME);
        String source = "Source Test";
        String translation = fish.translate(source);
        Assertions.assertNotEquals(source, translation);
    }
}
