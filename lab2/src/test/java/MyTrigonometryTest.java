import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import trigonometrical.MyTrigonometry;

import java.util.Arrays;
import java.util.stream.Stream;

public class MyTrigonometryTest {
    private static final double FLOATING_POINT_TOLERANCE = 1E-2;
    private final MyTrigonometry trigonometryModule = new MyTrigonometry(FLOATING_POINT_TOLERANCE / 2);

    private static final double[] sinArgs = {-4 * Math.PI, -Math.PI / 2, - Math.PI / 3, Math.PI / 6, Math.PI / 4, 2 * Math.PI / 3, 10 * Math.PI, 0, 1, -1};
    private static Stream<Arguments> sinArgsStream() {
        return Arrays.stream(sinArgs).mapToObj(Arguments::of);
    }
    @ParameterizedTest
    @MethodSource("sinArgsStream")
    public void testSin(double arg) {
        Assertions.assertEquals(Math.sin(arg), trigonometryModule.sin(arg), FLOATING_POINT_TOLERANCE);
    }

    @ParameterizedTest
    @MethodSource("sinArgsStream")
    public void testSinPeriod(double base) {
        double baseValue = trigonometryModule.sin(base);
        for (int i = -10; i < 10; ++i) {
            double arg = base + i * Math.PI;
            double val = trigonometryModule.sin(arg);
            if (i % 2 == 0) {
                Assertions.assertEquals(baseValue, val, FLOATING_POINT_TOLERANCE);
            } else {
                Assertions.assertEquals(-baseValue, val, FLOATING_POINT_TOLERANCE);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("sinArgsStream")
    public void testSinOdd(double arg) {
        double val1 = trigonometryModule.sin(arg);
        double val2 = trigonometryModule.sin(-arg);
        Assertions.assertEquals(-val1, val2, FLOATING_POINT_TOLERANCE);
    }

    private static final double[] cosArgs = {-4 * Math.PI, -Math.PI / 2, - Math.PI / 3, Math.PI / 6, Math.PI/4, 2 * Math.PI / 3, 10 * Math.PI, 0, 1, -1};
    private static Stream<Arguments> cosArgsStream() {
        return Arrays.stream(cosArgs).mapToObj(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("cosArgsStream")
    public void testCos(double arg) {
        Assertions.assertEquals(Math.cos(arg), trigonometryModule.cos(arg), FLOATING_POINT_TOLERANCE);
    }

    @ParameterizedTest
    @MethodSource("cosArgsStream")
    public void testCosPeriod(double base) {
        double baseValue = trigonometryModule.cos(base);
        for (int i = -10; i < 10; ++i) {
            double arg = base + i * Math.PI;
            double val = trigonometryModule.cos(arg);
            if (i % 2 == 0) {
                Assertions.assertEquals(baseValue, val, FLOATING_POINT_TOLERANCE);
            } else {
                Assertions.assertEquals(-baseValue, val, FLOATING_POINT_TOLERANCE);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("cosArgsStream")
    public void testCosEven(double arg) {
        double val1 = trigonometryModule.cos(arg);
        double val2 = trigonometryModule.cos(-arg);
        Assertions.assertEquals(val1, val2, FLOATING_POINT_TOLERANCE);
    }

    private static final double[] tanArgs = {-4, -Math.PI, -Math.PI / 2 + FLOATING_POINT_TOLERANCE, -Math.PI / 4, 0, Math.PI / 3, Math.PI / 6, 2 * Math.PI / 3, 10};
    private static Stream<Arguments> tanArgsStream() {
        return Arrays.stream(tanArgs).mapToObj(Arguments::of);
    }
    @ParameterizedTest
    @MethodSource("tanArgsStream")
    public void testTan(double arg) {
        Assertions.assertEquals(Math.tan(arg), trigonometryModule.tan(arg), FLOATING_POINT_TOLERANCE);
    }

    @ParameterizedTest
    @MethodSource("tanArgsStream")
    public void testTanPeriod(double base) {
        double baseValue = trigonometryModule.tan(base);
        for (int i = -10; i < 10; ++i) {
            double arg = base + i * Math.PI;
            double val = trigonometryModule.tan(arg);
            Assertions.assertEquals(baseValue, val, FLOATING_POINT_TOLERANCE);
        }
    }

    @ParameterizedTest
    @MethodSource("tanArgsStream")
    public void testTanOdd(double arg) {
        double val1 = trigonometryModule.tan(arg);
        double val2 = trigonometryModule.tan(-arg);
        Assertions.assertEquals(-val1, val2, FLOATING_POINT_TOLERANCE);
    }

    private static final double[] cotArgs = {-4, -Math.PI + FLOATING_POINT_TOLERANCE, -Math.PI / 2 + FLOATING_POINT_TOLERANCE, -Math.PI / 4, 0, Math.PI / 3, Math.PI / 6, 2 * Math.PI / 3, 10};
    private static Stream<Arguments> cotArgsStream() {
        return Arrays.stream(cotArgs).mapToObj(Arguments::of);
    }
    @ParameterizedTest
    @MethodSource("cotArgsStream")
    public void testCot(double arg) {
        Assertions.assertEquals(1 / Math.tan(arg), trigonometryModule.cot(arg), FLOATING_POINT_TOLERANCE);
    }

    @ParameterizedTest
    @MethodSource("cotArgsStream")
    public void testCotPeriod(double base) {
        double baseValue = trigonometryModule.cot(base);
        for (int i = -10; i < 10; ++i) {
            double arg = base + i * Math.PI;
            double val = trigonometryModule.cot(arg);
            Assertions.assertEquals(baseValue, val, FLOATING_POINT_TOLERANCE);
        }
    }

    @ParameterizedTest
    @MethodSource("cotArgsStream")
    public void testCotOdd(double arg) {
        double val1 = trigonometryModule.tan(arg);
        double val2 = trigonometryModule.tan(-arg);
        Assertions.assertEquals(-val1, val2, FLOATING_POINT_TOLERANCE);
    }

    private static final double[] secArgs = {-42, -Math.PI, -Math.PI / 2 + FLOATING_POINT_TOLERANCE, -Math.PI / 4, 0, Math.PI / 3, Math.PI / 6, 2 * Math.PI / 3, 10};
    private static Stream<Arguments> secArgsStream() {
        return Arrays.stream(secArgs).mapToObj(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("secArgsStream")
    public void testSec(double arg) {
        Assertions.assertEquals(1 / Math.cos(arg), trigonometryModule.sec(arg), FLOATING_POINT_TOLERANCE);
    }

    @ParameterizedTest
    @MethodSource("secArgsStream")
    public void testSecPeriod(double base) {
        double baseValue = trigonometryModule.sec(base);
        for (int i = -10; i < 10; ++i) {
            double arg = base + i * Math.PI;
            double val = trigonometryModule.sec(arg);
            if (i % 2 == 0) {
                Assertions.assertEquals(baseValue, val, FLOATING_POINT_TOLERANCE);
            } else {
                Assertions.assertEquals(-baseValue, val, FLOATING_POINT_TOLERANCE);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("secArgsStream")
    public void testSecEven(double arg) {
        double val1 = trigonometryModule.sec(arg);
        double val2 = trigonometryModule.sec(-arg);
        Assertions.assertEquals(val1, val2, FLOATING_POINT_TOLERANCE);
    }
}
