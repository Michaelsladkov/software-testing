package task3;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Underwear implements Takeable, Dirtable {
    @Getter
    private String name = "an underwear";

    @Getter
    private DirtableState state = DirtableState.CLEAN;

    @Getter
    private Person owner = null;

    public Underwear(String name, Person owner) {
        this.name = name;
        this.owner = owner;
    }

    public Underwear(String name) {
        this.name = name;
    }

    @Override
    public void makeMoreDirty() {
        if (state.ordinal() < DirtableState.DIRTY.ordinal()) {
            state = DirtableState.values()[state.ordinal() + 1];
        }
    }

    @Override
    public void wash() {
        state = DirtableState.CLEAN;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode() + state.ordinal() * 39;
        return owner != null ? result + owner.hashCode() : result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Underwear other))
            return false;
        boolean result = other.name.equals(name);
        if (owner != null)
            result &= other.owner.equals(owner);
        return result;
    }

    @Override
    public String toString() {
        String result = "Underwear named " + name + " is in " + state.toString() +" state.";
        if (owner != null) {
            result += owner.toString() + " owns it";
        }
        return result;
    }
}
