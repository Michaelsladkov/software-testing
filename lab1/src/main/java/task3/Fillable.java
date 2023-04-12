package task3;

public interface Fillable extends Item {
    void fillWith(Takeable item);
    Takeable extract();
}
