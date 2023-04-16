package logaritmical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.PrintWriter;

@NoArgsConstructor
@AllArgsConstructor
public class MyLn {
    @Getter
    private double precision = 0.01;

    public double calculate(double arg) {
        if (Double.isNaN(arg)) return  Double.NaN;
        if (arg < 0) return Double.NaN;
        if (arg == 0) return Double.NEGATIVE_INFINITY;
        if (Double.isInfinite(arg)) return arg;
        double x = arg > 1 ? (1 / arg - 1) : arg - 1;
        double result = x;
        double cur = -x*x;
        int iter = 2;
        int sign = -1;
        while(Math.abs(sign * Math.pow(x, iter) / (iter + 1) + cur / iter) > precision / iter) {
            result += cur/iter++;
            sign = -sign;
            cur = sign * Math.pow(x, iter);
        }
        return arg > 1 ? -result : result;
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
