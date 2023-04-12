package task3;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LittleYellowFish implements Creature, Takeable{
    @Getter
    private String name = "some little yellow fish";

    public LittleYellowFish(String name) {
        this.name = name;
    }

    public String translate(String message) {
        return "Let's think it is a translation of " + message;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LittleYellowFish other))
            return false;
        return other.name.equals(name);
    }

    @Override
    public String toString() {
        return "Little Yellow Fish named: " + name;
    }
}
