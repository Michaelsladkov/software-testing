package task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ShellSortTest {
    private static ShellSort<Double> shellSort;

    @BeforeAll
    public static void init() {
        shellSort = new ShellSort<>();
    }

    @Test
    public void oddNumberTests() {
        Double[] source = {5.0, 3.0, 7.0, 1.0, 12.0, 0.0, -42.0};
        Double[] sorted = {-42.0, 0.0, 1.0, 3.0, 5.0, 7.0, 12.0};
        shellSort.sort(source);
        Assertions.assertArrayEquals(source, sorted);
    }

    @Test
    public void evenNumberTests() {
        Double[] source = {5.0, 3.0, 7.0, 1.0, 69.0, 12.0, 0.0, -42.0};
        Double[] sorted = {-42.0, 0.0, 1.0, 3.0, 5.0, 7.0, 12.0, 69.0};
        shellSort.sort(source);
        Assertions.assertArrayEquals(source, sorted);
    }

    @Test
    public void nullTest() {
        Double[] source = null;
        Double[] sorted = null;
        shellSort.sort(source);
        Assertions.assertArrayEquals(source, sorted);

    }

    @Test
    public void testEmpty() {
        Double[] source = {};
        Double[] sorted = {};
        shellSort.sort(source);
        Assertions.assertArrayEquals(source, sorted);
    }

    @Test
    public void testSingleElement() {
        Double[] source = {Math.PI};
        Double[] sorted = {Math.PI};
        shellSort.sort(source);
        Assertions.assertArrayEquals(source, sorted);
    }

    @Test
    public void testRepeatedElements() {
        Double[] source = {Double.NaN, Double.POSITIVE_INFINITY, Math.PI, Math.E, -0.0, 1.1, 0.0, 0.0, Math.PI, Math.E, Math.PI, Double.POSITIVE_INFINITY, Double.NaN, Double.NEGATIVE_INFINITY};
        Double[] sorted = {Double.NEGATIVE_INFINITY, -0.0, 0.0, 0.0, 1.1, Math.E, Math.E, Math.PI, Math.PI, Math.PI, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NaN, Double.NaN};
        shellSort.sort(source);
        Assertions.assertArrayEquals(source, sorted);
    }

    @Test
    public void sortingLogTest() {
        Double[] source = {4.0, 2.0, 5.0, 0.0};
        Double[][] states = {
                {4.0, 2.0, 5.0, 0.0},
                {4.0, 0.0, 5.0, 2.0},
                {0.0, 4.0, 5.0, 2.0},
                {0.0, 4.0, 5.0, 2.0},
                {0.0, 2.0, 4.0, 5.0}
        };
        shellSort.sort(source);
        Assertions.assertArrayEquals(states, shellSort.getSortingLog().toArray());
    }
}
