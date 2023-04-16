import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TaskFunctionTest {
    private static final double FLOATING_POINT_TOLERANCE = 1E-4;

    TaskFunction function = new TaskFunction(FLOATING_POINT_TOLERANCE/10);
    private static Stream<Arguments> testValues() {
        return Stream.of(
                Arguments.of(-42, -1.8333155),
                Arguments.of(-9*Math.PI, Double.NaN),
                Arguments.of(-Math.PI - 0.01, 300.02490),
                Arguments.of(-Math.PI + 0.01, -300.02510),
                Arguments.of(-2*Math.PI/3, -14.660254),
                Arguments.of(-Math.PI/3, 0.8038476),
                Arguments.of(-Math.PI/4, -3.2426407),
                Arguments.of(-Math.PI/6, -5.7044162),
                Arguments.of(-Math.PI/42, -40.139048),
                Arguments.of(-1, -0.4010639),
                Arguments.of(-0.01, -300.00490),
                Arguments.of(0, Double.NaN),
                Arguments.of(0.01, 7.2041200),
                Arguments.of(0.5, 0.5728872),
                Arguments.of(1, Double.NaN),
                Arguments.of(2, -0.3916491),
                Arguments.of(Math.E, -0.5071541),
                Arguments.of(4, -0.6020600),
                Arguments.of(6, -0.6411256),
                Arguments.of(8, -0.6312328),
                Arguments.of(10, -0.6020600),
                Arguments.of(16, -0.4791675),
                Arguments.of(42, 0.0343955)
        );
    }

    @ParameterizedTest
    @MethodSource("testValues")
    public void testFunction(double arg, double expected) {
        Assertions.assertEquals(expected, function.calculate(arg), FLOATING_POINT_TOLERANCE);
    }
}
