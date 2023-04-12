package task1;

public class ArcTg {
    private static final double TOLERANCE = 10E-7;

    public static double calculate(double arg) {
        if (Double.isNaN(arg))
            return arg;
        if (Double.isInfinite(arg))
            return Math.copySign(Math.PI / 2, arg);
        boolean switchAngle = Math.abs(arg) > 1;
        if (switchAngle)
            arg = 1 / arg;
        int n = 0;
        double ans = 0;
        double diff = 1;
        while (Math.abs(diff) > TOLERANCE) {
            diff = Math.pow(arg, 2 * n + 1) / (2 * n + 1);
            diff = n % 2 == 0 ? diff : -diff;
            ans += diff;
            ++n;
        }
        return switchAngle ? Math.copySign(Math.PI / 2, arg) - ans : ans;
    }
}
