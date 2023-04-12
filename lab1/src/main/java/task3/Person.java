package task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class Person implements Creature{
    @Getter
    private Origin origin;
    @Getter
    private String name;

    @Getter
    @Setter
    private Takeable handsContent;

    @Getter
    private Visible centerOfConcentration;

    @Getter
    private Set<Visible> fieldOfVision;

    public Person(String name, Origin origin) {
        this.name = name;
        this.origin = origin;
        centerOfConcentration = null;
        handsContent = null;
        fieldOfVision = new HashSet<>();
    }

    public void lookAt(Visible item) {
        centerOfConcentration = item;
        fieldOfVision.add(item);
    }

    public void addToFOV(Visible item) {
        fieldOfVision.add(item);
    }

    public boolean removeFromFOV(Visible item) {
        if (item == null)
            return false;
        if (centerOfConcentration != null && centerOfConcentration.equals(item)) {
            centerOfConcentration = null;
        }
        return fieldOfVision.remove(item);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + origin.ordinal() * 42;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person other))
            return false;
        return other.getName().equals(name) && other.origin == origin;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Person named ")
                .append(name)
                .append(" came from ")
                .append(origin.toString())
                .append(". ");
        if (handsContent != null)
            stringBuilder.append(handsContent.toString()).append(" in his hands. ");
        if (centerOfConcentration != null)
            stringBuilder.append("Looks at ").append(centerOfConcentration).append(". ");
        if (!fieldOfVision.isEmpty()) {
            stringBuilder.append("Sees: ");
            for (Visible v : fieldOfVision) {
                stringBuilder.append(v.toString());
                stringBuilder.append("; ");
            }
        }
        return stringBuilder.toString();
    }
}
