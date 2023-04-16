import logaritmical.MyLn;
import logaritmical.MyLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import trigonometrical.MySin;
import trigonometrical.MyTrigonometry;


import java.util.stream.Stream;

import static org.mockito.Mockito.when;

public class IntegrationTest {
    private static final double FLOATING_POINT_TOLERANCE = 1E-1;

    private static Stream<Arguments> testValues() {
        return Stream.of(
                Arguments.of(-Math.PI - 0.01, 300.02490),
                Arguments.of(-Math.PI + 0.01, -300.02510),
                Arguments.of(-2*Math.PI/3, -14.660254),
                Arguments.of(-Math.PI/3, 0.8038476),
                Arguments.of(-Math.PI/4, -3.2426407),
                Arguments.of(-Math.PI/6, -5.7044162),
                Arguments.of(-1, -0.4010639),
                Arguments.of(-0.01, -300.00490),
                Arguments.of(0, Double.NaN),
                Arguments.of(0.01, 7.2041200),
                Arguments.of(0.5, 0.5728872),
                Arguments.of(1, Double.NaN),
                Arguments.of(2, -0.3916491),
                Arguments.of(Math.E, -0.5071541),
                Arguments.of(4, -0.6020600)
        );
    }

    static MyLn lnMock = Mockito.spy(MyLn.class);
    static {
        when(lnMock.getPrecision()).thenReturn(FLOATING_POINT_TOLERANCE/10);
        when(lnMock.calculate(-1)).thenReturn(Double.NaN);
        when(lnMock.calculate(-0.01)).thenReturn(Double.NaN);
        when(lnMock.calculate(0)).thenReturn(Double.NaN);
        when(lnMock.calculate(0.01)).thenReturn(-4.6051702);
        when(lnMock.calculate(0.5)).thenReturn(-0.6931472);
        when(lnMock.calculate(Double.POSITIVE_INFINITY)).thenReturn(Double.POSITIVE_INFINITY);
        when(lnMock.calculate(1)).thenReturn(0.0);
        when(lnMock.calculate(2)).thenReturn(0.6931472);
        when(lnMock.calculate(Math.E)).thenReturn(1.0);
        when(lnMock.calculate(4)).thenReturn(1.3862944);
    }

    static MyLog logMock = Mockito.spy(MyLog.class);
    static {
        when(logMock.getPrecision()).thenReturn(FLOATING_POINT_TOLERANCE/10);
        when(logMock.log(-1, 2)).thenReturn(Double.NaN);
        when(logMock.log(-0.01, 2)).thenReturn(Double.NaN);
        when(logMock.log(0, 2)).thenReturn(Double.NaN);
        when(logMock.log(0.01, 2)).thenReturn(-6.6438562);
        when(logMock.log(0.5, 2)).thenReturn(-1.0);
        when(logMock.log(Double.POSITIVE_INFINITY, 2)).thenReturn(Double.POSITIVE_INFINITY);
        when(logMock.log(1, 2)).thenReturn(0.0);
        when(logMock.log(2, 2)).thenReturn(1.0);
        when(logMock.log(Math.E, 2)).thenReturn(1.4426950);
        when(logMock.log(4, 2)).thenReturn(2.0);
        when(logMock.log(-1, 10)).thenReturn(Double.NaN);
        when(logMock.log(-0.01, 10)).thenReturn(Double.NaN);
        when(logMock.log(0, 10)).thenReturn(Double.NaN);
        when(logMock.log(0.01, 10)).thenReturn(-2.0);
        when(logMock.log(0.5, 10)).thenReturn(-0.3010300);
        when(logMock.log(Double.POSITIVE_INFINITY, 10)).thenReturn(Double.POSITIVE_INFINITY);
        when(logMock.log(1, 10)).thenReturn(0.0);
        when(logMock.log(2, 10)).thenReturn(0.3010300);
        when(logMock.log(Math.E, 10)).thenReturn(0.4342945);
        when(logMock.log(4, 10)).thenReturn(0.6020600);
    }

    static MySin sinMock = Mockito.spy(MySin.class);
    static {
        when(sinMock.calculate(-Math.PI - 0.01)).thenReturn(0.009999999999998);
        when(sinMock.calculate(-Math.PI)).thenReturn(0.0);
        when(sinMock.calculate(-Math.PI + 0.01)).thenReturn(-0.009999999999998);
        when(sinMock.calculate(-2*Math.PI/3)).thenReturn(-0.8660254);
        when(sinMock.calculate(-Math.PI/3)).thenReturn(-0.8660254);
        when(sinMock.calculate(-1)).thenReturn(-0.8414710);
        when(sinMock.calculate(-0.01)).thenReturn(-0.009999999999998);
        when(sinMock.calculate(-Math.PI/4)).thenReturn(-0.7071068);
        when(sinMock.calculate(-Math.PI/6)).thenReturn(-0.5);
        when(sinMock.calculate(0)).thenReturn(0.0);
        when(sinMock.calculate(Math.PI/6)).thenReturn(0.5);
        when(sinMock.calculate(Math.PI/4)).thenReturn(0.7071068);
        when(sinMock.calculate(Math.PI/3)).thenReturn(0.8660254);
        when(sinMock.calculate(2*Math.PI/3)).thenReturn(0.8660254);
        when(sinMock.calculate(Math.PI)).thenReturn(0.0);
    }

    static MyTrigonometry trigonometryMock = Mockito.spy(MyTrigonometry.class);
    static {
        when(trigonometryMock.sin(-Math.PI - 0.01)).thenReturn(0.00999999998);
        when(trigonometryMock.sin(-Math.PI)).thenReturn(0.0);
        when(trigonometryMock.sin(-Math.PI + 0.01)).thenReturn(-0.00999999998);
        when(trigonometryMock.sin(-2*Math.PI/3)).thenReturn(-0.8660254);
        when(trigonometryMock.sin(-Math.PI/3)).thenReturn(-0.8660254);
        when(trigonometryMock.sin(-1)).thenReturn(-0.8414710);
        when(trigonometryMock.sin(-0.01)).thenReturn(-0.0099998);
        when(trigonometryMock.sin(-Math.PI/4)).thenReturn(-0.7071068);
        when(trigonometryMock.sin(-Math.PI/6)).thenReturn(-0.5);
        when(trigonometryMock.sin(0)).thenReturn(0.0);
        when(trigonometryMock.sin(Math.PI/6)).thenReturn(0.5);
        when(trigonometryMock.sin(Math.PI/4)).thenReturn(0.7071068);
        when(trigonometryMock.sin(Math.PI/3)).thenReturn(0.8660254);
        when(trigonometryMock.sin(2*Math.PI/3)).thenReturn(0.8660254);
        when(trigonometryMock.sin(Math.PI)).thenReturn(0.0);

        when(trigonometryMock.cos(-Math.PI - 0.01)).thenReturn(-0.9999500);
        when(trigonometryMock.cos(-Math.PI)).thenReturn(-1.0);
        when(trigonometryMock.cos(-Math.PI + 0.01)).thenReturn(-0.9999500);
        when(trigonometryMock.cos(-2*Math.PI/3)).thenReturn(-0.5);
        when(trigonometryMock.cos(-Math.PI/3)).thenReturn(0.5);
        when(trigonometryMock.cos(-1)).thenReturn(0.5403023);
        when(trigonometryMock.cos(-0.01)).thenReturn(0.9999500);
        when(trigonometryMock.cos(-Math.PI/4)).thenReturn(0.7071068);
        when(trigonometryMock.cos(-Math.PI/6)).thenReturn(0.8660254);
        when(trigonometryMock.cos(0)).thenReturn(1.0);
        when(trigonometryMock.cos(Math.PI/6)).thenReturn(0.8660254);
        when(trigonometryMock.cos(Math.PI/4)).thenReturn(0.7071068);
        when(trigonometryMock.cos(Math.PI/3)).thenReturn(0.5);
        when(trigonometryMock.cos(2*Math.PI/3)).thenReturn(0.5);
        when(trigonometryMock.cos(Math.PI)).thenReturn(-1.0);

        when(trigonometryMock.sec(-Math.PI - 0.01)).thenReturn(-1/0.9999500);
        when(trigonometryMock.sec(-Math.PI)).thenReturn(-1.0);
        when(trigonometryMock.sec(-Math.PI + 0.01)).thenReturn(-1/0.9999500);
        when(trigonometryMock.sec(-2*Math.PI/3)).thenReturn(-1/0.5);
        when(trigonometryMock.sec(-Math.PI/3)).thenReturn(1/0.5);
        when(trigonometryMock.sec(-1)).thenReturn(1/0.5403023);
        when(trigonometryMock.sec(-0.01)).thenReturn(1/0.9999500);
        when(trigonometryMock.sec(-Math.PI/4)).thenReturn(1/0.7071068);
        when(trigonometryMock.sec(-Math.PI/6)).thenReturn(1/0.8660254);
        when(trigonometryMock.sec(0)).thenReturn(1.0);
        when(trigonometryMock.sec(Math.PI/6)).thenReturn(1/0.8660254);
        when(trigonometryMock.sec(Math.PI/4)).thenReturn(1/0.7071068);
        when(trigonometryMock.sec(Math.PI/3)).thenReturn(1/0.5);
        when(trigonometryMock.sec(2*Math.PI/3)).thenReturn(1/0.5);
        when(trigonometryMock.sec(Math.PI)).thenReturn(-1.0);

        when(trigonometryMock.tan(-Math.PI - 0.01)).thenReturn(-0.01000000003);
        when(trigonometryMock.tan(-Math.PI)).thenReturn(0.0);
        when(trigonometryMock.tan(-Math.PI + 0.01)).thenReturn(0.01000000003);
        when(trigonometryMock.tan(-2*Math.PI/3)).thenReturn(1.7320508);
        when(trigonometryMock.tan(-Math.PI/3)).thenReturn(-1.7320508);
        when(trigonometryMock.tan(-1)).thenReturn(-1.5574077);
        when(trigonometryMock.tan(-0.01)).thenReturn(-0.0100003);
        when(trigonometryMock.tan(-Math.PI/4)).thenReturn(-1.0);
        when(trigonometryMock.tan(-Math.PI/6)).thenReturn(-0.5773503);
        when(trigonometryMock.tan(0)).thenReturn(0.0);
        when(trigonometryMock.tan(Math.PI/6)).thenReturn(0.5773503);
        when(trigonometryMock.tan(Math.PI/4)).thenReturn(1.0);
        when(trigonometryMock.tan(Math.PI/3)).thenReturn(1.7320508);
        when(trigonometryMock.tan(2*Math.PI/3)).thenReturn(-1.7320508);
        when(trigonometryMock.tan(Math.PI)).thenReturn(0.0);

        when(trigonometryMock.cot(-Math.PI - 0.01)).thenReturn(-1/0.01000000003);
        when(trigonometryMock.cot(-Math.PI)).thenReturn(Double.POSITIVE_INFINITY);
        when(trigonometryMock.cot(-Math.PI + 0.01)).thenReturn(1/0.01000000003);
        when(trigonometryMock.cot(-2*Math.PI/3)).thenReturn(1/1.7320508);
        when(trigonometryMock.cot(-Math.PI/3)).thenReturn(-1/1.7320508);
        when(trigonometryMock.cot(-1)).thenReturn(-1/1.5574077);
        when(trigonometryMock.cot(-0.01)).thenReturn(-1/0.0100003);
        when(trigonometryMock.cot(-Math.PI/4)).thenReturn(-1.0);
        when(trigonometryMock.cot(-Math.PI/6)).thenReturn(-1/0.5773503);
        when(trigonometryMock.cot(0)).thenReturn(Double.POSITIVE_INFINITY);
        when(trigonometryMock.cot(Math.PI/6)).thenReturn(1/0.5773503);
        when(trigonometryMock.cot(Math.PI/4)).thenReturn(1.0);
        when(trigonometryMock.cot(Math.PI/3)).thenReturn(1/1.7320508);
        when(trigonometryMock.cot(2*Math.PI/3)).thenReturn(-1/1.7320508);
        when(trigonometryMock.cot(Math.PI)).thenReturn(Double.NEGATIVE_INFINITY);
    }

    @ParameterizedTest
    @MethodSource("testValues")
    public void testWithHighLevelMocks(double arg, double expected) {
        TaskFunction tf = new TaskFunction(logMock, trigonometryMock);
        Assertions.assertEquals(expected, tf.calculate(arg), FLOATING_POINT_TOLERANCE);
    }

    @ParameterizedTest
    @MethodSource("testValues")
    public void testWithTrigonometryAndMockedSinus(double arg, double expected) {
        MyTrigonometry trigonometry = new MyTrigonometry(sinMock);
        TaskFunction tf = new TaskFunction(logMock, trigonometry);
        Assertions.assertEquals(expected, tf.calculate(arg), FLOATING_POINT_TOLERANCE);
    }

    @ParameterizedTest
    @MethodSource("testValues")
    public void testWithLogAndMockedLn(double arg, double expected) {
        MyLog log = new MyLog(lnMock);
        TaskFunction tf = new TaskFunction(log, trigonometryMock);
        Assertions.assertEquals(expected, tf.calculate(arg), FLOATING_POINT_TOLERANCE);
    }

    @ParameterizedTest
    @MethodSource("testValues")
    public void testWithLowLevelMocks(double arg, double expected) {
        MyLog log = new MyLog(lnMock);
        MyTrigonometry trigonometry = new MyTrigonometry(sinMock);
        TaskFunction tf = new TaskFunction(log, trigonometry);
        Assertions.assertEquals(expected, tf.calculate(arg), FLOATING_POINT_TOLERANCE);
    }
}
