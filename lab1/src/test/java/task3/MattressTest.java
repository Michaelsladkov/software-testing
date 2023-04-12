package task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MattressTest {
    private static final String NAME = "mattress";
    private static final String DEFAULT_NAME = "a mattress";

    @Test
    public void defaultNameTest() {
        Mattress mattress = new Mattress();
        Assertions.assertEquals(mattress.getName(), DEFAULT_NAME);
    }

    @Test
    public void setNameTest() {
        Mattress mattress = new Mattress(NAME);
        Assertions.assertEquals(mattress.getName(), NAME);
    }

    @Test
    public void lyingTest() {
        Mattress mattress = new Mattress();
        Person person = new Person("Ford", Origin.BETELGEUSE);
        mattress.setLyingCreature(person);
        Assertions.assertEquals(mattress.getLyingCreature(), person);
    }

    @Test
    public void dirtyingTest() {
        Mattress mattress = new Mattress();
        Assertions.assertEquals(mattress.getState(), DirtableState.CLEAN);
        mattress.makeMoreDirty();
        Assertions.assertEquals(mattress.getState(), DirtableState.A_FEW_SPOTS);
        mattress.makeMoreDirty();
        Assertions.assertEquals(mattress.getState(), DirtableState.DIRTY);
        mattress.makeMoreDirty();
        Assertions.assertEquals(mattress.getState(), DirtableState.DIRTY);
    }

    @Test
    public void washingTest() {
        Mattress mattress = new Mattress();
        mattress.makeMoreDirty();
        mattress.makeMoreDirty();
        mattress.makeMoreDirty();
        Assertions.assertNotEquals(mattress.getState(), DirtableState.CLEAN);
        mattress.wash();
        Assertions.assertEquals(mattress.getState(), DirtableState.CLEAN);
    }
}
