package task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ArcTgTest {
    private static double DELTA;
    private static double STEP;

    @BeforeAll
    static void init() {
        DELTA = 10e-6;
        STEP = 0.01;
    }

    /**
     * Тестирование методом граничных значений.
     *
     * @param param очередное значение функции
     */
    @ParameterizedTest
    @ValueSource(doubles = {-42.0, -1.0001, -1.0, -0.9999, -0.0001, 0, 42.0, 1.0001, 1.0, 0.9999, 0.0001, Double.NaN, -1 * Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    public void generalTest(double param) {
        Assertions.assertEquals(Math.atan(param), ArcTg.calculate(param), DELTA);
    }

    @ParameterizedTest
    @ValueSource(doubles = {42.0, 1.0001, 1.0, 0.9999, 0.0001, 0, Double.POSITIVE_INFINITY})
    public void symmetricalTest(double param) {
        Assertions.assertEquals(ArcTg.calculate(param), -1 * ArcTg.calculate(-1 * param), DELTA);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.5, 1.01, 1.0, 0.99, 0.01, 0, -0.01, -0.99, -1.0, -1.01, -1.5})
    public void monotonousTest(double param) {
        double val1 = ArcTg.calculate(param - STEP);
        double val2 = ArcTg.calculate(param);
        double val3 = ArcTg.calculate(param + STEP);
        Assertions.assertTrue(val1 < val2);
        Assertions.assertTrue(val2 < val3);
    }
}
