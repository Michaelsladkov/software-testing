package task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnderwearTest {
    private static final String NAME = "underwear under test";
    private static final String DEFAULT_NAME = "an underwear";

    @Test
    public void defaultNameTest() {
        Underwear underwear = new Underwear();
        Assertions.assertEquals(underwear.getName(), DEFAULT_NAME);
    }

    @Test
    public void setNameTest() {
        Underwear underwear = new Underwear(NAME);
        Assertions.assertEquals(underwear.getName(), NAME);
    }

    @Test
    public void ownerTest() {
        Person person = new Person("Ford", Origin.BETELGEUSE);
        Underwear underwear = new Underwear(NAME, person);
        Assertions.assertEquals(underwear.getOwner(), person);
    }

    @Test
    public void dirtyingTest() {
        Underwear underwear = new Underwear(NAME);
        Assertions.assertEquals(underwear.getState(), DirtableState.CLEAN);
        underwear.makeMoreDirty();
        Assertions.assertEquals(underwear.getState(), DirtableState.A_FEW_SPOTS);
        underwear.makeMoreDirty();
        Assertions.assertEquals(underwear.getState(), DirtableState.DIRTY);
        underwear.makeMoreDirty();
        Assertions.assertEquals(underwear.getState(), DirtableState.DIRTY);
    }

    @Test
    public void washingTest() {
        Underwear underwear = new Underwear();
        underwear.makeMoreDirty();
        underwear.makeMoreDirty();
        underwear.makeMoreDirty();
        Assertions.assertNotEquals(underwear.getState(), DirtableState.CLEAN);
        underwear.wash();
        Assertions.assertEquals(underwear.getState(), DirtableState.CLEAN);
    }
}
