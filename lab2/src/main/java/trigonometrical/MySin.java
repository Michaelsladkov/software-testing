package trigonometrical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.PrintWriter;

@AllArgsConstructor
@NoArgsConstructor
public class MySin {
    @Getter
    private double precision = 0.01;

    public double calculate(double arg) {
        if (Double.isNaN(arg)) return arg;
        if (Double.isInfinite(arg)) return Double.NaN;
        if (arg > 0) {
            while (arg > Math.PI) {
                arg -= 2 * Math.PI;
            }
        } else if (arg < 0) {
            while (arg < -Math.PI) {
                arg += 2 * Math.PI;
            }
        }
        double cur = 1;
        int iter = 1;
        double res = 0;
        while (Math.abs(cur) > precision && iter < 100) {
            cur *= arg / iter;
            if (iter % 4 == 1) res += cur;
            if (iter % 4 == 3) res -= cur;
            ++iter;
        }
        return res;
    }

    public void writeCSV(double x, PrintWriter out) {
        double res = calculate(x);
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
