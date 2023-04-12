package trigonometrical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

@AllArgsConstructor
public class MySin {
    @Getter
    private double tolerance;

    public double calculate(double arg) {
        if (Double.isNaN(arg)) return arg;
        if (Double.isInfinite(arg)) return Double.NaN;
        int factorial = 1;
        double cur = arg;
        int iter = 1;
        double res = 0;
        while (Math.abs(cur) > tolerance) {
            factorial = factorial * (2 * iter - 1);
            int sign = iter % 2 == 0 ? -1 : 1;
            res += Math.pow(arg, (2 * iter - 1)) * sign / factorial;
            ++iter;
        }
        return res;
    }

    public void writeCSV(double x, Writer out) {
        double res = calculate(x);
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
