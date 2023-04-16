import logaritmical.MyLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MyLogTest {
    private static final double FLOATING_PONT_TOLERANCE = 1E-6;
    MyLog logModule = new MyLog(FLOATING_PONT_TOLERANCE / 2);

    @ParameterizedTest
    @ValueSource(doubles = {-1, 0, 10E-4, 0.33, 0.5, 1, Math.E, Math.E * Math.E, 10, 42, Double.NaN, Double.POSITIVE_INFINITY})
    public void testLn(double arg) {
        Assertions.assertEquals(Math.log(arg), logModule.log(arg, Math.E), FLOATING_PONT_TOLERANCE);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, 0, 10E-4, 0.1, 0.33, 0.5, 1, 10, 100, 1000, 42, Double.NaN, Double.POSITIVE_INFINITY})
    public void testLog10(double arg) {
        Assertions.assertEquals(Math.log10(arg), logModule.log(arg, 10), FLOATING_PONT_TOLERANCE);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, 0, 0.125, 0.25, 0.33, 0.5, 1, 2, 4, 8, 65536, 10, 42, Double.NaN, Double.POSITIVE_INFINITY})
    public void testLog2(double arg) {
        Assertions.assertEquals(Math.log10(arg), logModule.log(arg, 10), FLOATING_PONT_TOLERANCE);
    }
}
