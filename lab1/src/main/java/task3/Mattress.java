package task3;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Mattress implements Item, Dirtable{
    @Getter
    private String name = "a mattress";

    @Getter
    private DirtableState state = DirtableState.CLEAN;

    @Getter
    @Setter
    private Creature lyingCreature = null;

    public Mattress(String name) {
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
        return name.hashCode() + state.ordinal() * 39;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Mattress other))
            return false;
        return other.name.equals(name);
    }

    @Override
    public String toString() {
        String result = "Mattress named " + name + " is in " + state.toString() +" state.";
        if (lyingCreature != null) {
            result += lyingCreature.toString() + " lies on it";
        }
        return result;
    }
}
