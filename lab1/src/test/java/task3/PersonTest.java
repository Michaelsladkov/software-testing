package task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonTest {
    private static final String NAME = "Person under test";
    private static final Origin ORIGIN = Origin.EARTH;

    @Test
    public void creationTest() {
        Person person = new Person(NAME, ORIGIN);
        Assertions.assertEquals(person.getName(), NAME);
        Assertions.assertEquals(person.getOrigin(), ORIGIN);
        Assertions.assertNotNull(person.getFieldOfVision());
    }

    @Test
    public void handsContentTest() {
        Person person = new Person(NAME, ORIGIN);
        Takeable bottle = new Bottle();
        Assertions.assertNull(person.getHandsContent());
        person.setHandsContent(bottle);
        Assertions.assertEquals(person.getHandsContent(), bottle);
    }

    @Test
    public void centerOfConcentrationTest() {
        Person person = new Person(NAME, ORIGIN);
        Visible bottle = new Bottle();
        Assertions.assertNull(person.getCenterOfConcentration());
        person.lookAt(bottle);
        Assertions.assertEquals(bottle, person.getCenterOfConcentration());
    }

    @Test
    public void simpleAddFOVTest() {
        Person person = new Person(NAME, ORIGIN);
        Visible bottle = new Bottle();
        Visible underwear = new Underwear();
        person.addToFOV(bottle);
        person.addToFOV(underwear);
        Assertions.assertEquals(person.getFieldOfVision().size(), 2);
        Assertions.assertTrue(person.getFieldOfVision().contains(bottle));
        Assertions.assertTrue(person.getFieldOfVision().contains(underwear));
    }

    @Test
    public void concentrationAddFOVTest() {
        Person person = new Person(NAME, ORIGIN);
        Visible bottle = new Bottle();
        Visible underwear = new Underwear();
        person.addToFOV(bottle);
        person.lookAt(underwear);
        Assertions.assertEquals(person.getFieldOfVision().size(), 2);
        Assertions.assertTrue(person.getFieldOfVision().contains(bottle));
        Assertions.assertTrue(person.getFieldOfVision().contains(underwear));
    }

    @Test
    public void removeFromFOVTest() {
        Person person = new Person(NAME, ORIGIN);
        Visible bottle = new Bottle();
        Visible underwear = new Underwear();
        person.addToFOV(bottle);
        person.lookAt(underwear);
        Assertions.assertEquals(person.getFieldOfVision().size(), 2);
        Assertions.assertTrue(person.getFieldOfVision().contains(bottle));
        Assertions.assertTrue(person.getFieldOfVision().contains(underwear));
        Assertions.assertTrue(person.removeFromFOV(bottle));
        Assertions.assertTrue(person.removeFromFOV(underwear));
        Assertions.assertNull(person.getCenterOfConcentration());
        Assertions.assertEquals(person.getFieldOfVision().size(), 0);
    }

    @Test
    public void removeNullFromFOVTest() {
        Person person = new Person(NAME, ORIGIN);
        Visible bottle = new Bottle();
        Visible underwear = new Underwear();
        person.addToFOV(bottle);
        person.lookAt(underwear);
        Assertions.assertEquals(person.getFieldOfVision().size(), 2);
        Assertions.assertFalse(person.removeFromFOV(null));
    }

    @Test
    public void removeFromEmptyFOVTest() {
        Person person = new Person(NAME, ORIGIN);
        Visible underwear = new Underwear();
        Assertions.assertFalse(person.removeFromFOV(underwear));
    }
}
