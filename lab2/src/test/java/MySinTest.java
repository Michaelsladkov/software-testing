import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import trigonometrical.MySin;

public class MySinTest {
    private static final double FLOATING_POINT_TOLERANCE = 10E-2;

    MySin sinModule = new MySin(FLOATING_POINT_TOLERANCE / 2);

    @ParameterizedTest
    @ValueSource(doubles = {-10, -Math.PI,-1, 0, 1E-4, 0.33, 0.5, 1, Math.PI, Math.E * Math.E, 10, 42, Double.NaN, Double.POSITIVE_INFINITY})
    public void generalSinTest(double param) {
        Assertions.assertEquals(sinModule.getPrecision(), FLOATING_POINT_TOLERANCE / 2);
        Assertions.assertEquals(sinModule.calculate(param), Math.sin(param), FLOATING_POINT_TOLERANCE);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.15, 0, 0.15, 1})
    public void periodicalTest(double base) {
        double baseValue = sinModule.calculate(base);
        for (int i = -10; i < 10; ++i) {
            double arg = base + i * Math.PI;
            double val = sinModule.calculate(arg);
            if (i % 2 == 0) {
                Assertions.assertEquals(baseValue, val, FLOATING_POINT_TOLERANCE);
            } else {
                Assertions.assertEquals(-baseValue, val, FLOATING_POINT_TOLERANCE);
            }
        }
    }
}
