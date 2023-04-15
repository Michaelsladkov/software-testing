import logaritmical.MyLn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MyLnTest {
    private static final double FLOATING_POINT_TOLERANCE = 10E-2;
    private static final double STEP = 10E-1;
    MyLn lnModule = new MyLn(FLOATING_POINT_TOLERANCE / 2);

    @ParameterizedTest
    @ValueSource(doubles = {-1, 0, 10E-4, 0.33, 0.5, 1, Math.E, Math.E * Math.E, 10, 42, Double.NaN, Double.POSITIVE_INFINITY})
    public void generalLnTest(double param) {
        Assertions.assertEquals(lnModule.getPrecision(), FLOATING_POINT_TOLERANCE / 2);
        Assertions.assertEquals(lnModule.calculate(param), Math.log(param), FLOATING_POINT_TOLERANCE);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.5, 1.01, 1.0, 0.99, 0.01})
    public void monotonousTest(double param) {
        double val1 = lnModule.calculate(param - STEP);
        double val2 = lnModule.calculate(param);
        double val3 = lnModule.calculate(param + STEP);
        Assertions.assertTrue(val1 < val2);
        Assertions.assertTrue(val2 < val3);
    }
}
