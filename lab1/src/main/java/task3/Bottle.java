package task3;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Bottle implements Takeable, Fillable{
    @Getter
    private Takeable contents = null;

    @Getter
    private String name = "a bottle";

    public Bottle(String name) {
        this.name = name;
    }

    @Override
    public void fillWith(Takeable item) {
        contents = item;
    }

    @Override
    public Takeable extract() {
        Takeable result = contents;
        contents = null;
        return result;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Bottle other))
            return false;
        return other.name.equals(name);
    }

    @Override
    public String toString() {
        return "A bottle named " +
                name +
                ". " +
                "It Contains " +
                contents.toString();
    }
}
