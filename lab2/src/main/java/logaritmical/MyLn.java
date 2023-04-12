package logaritmical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

@NoArgsConstructor
@AllArgsConstructor
public class MyLn {
    @Getter
    private double tolerance = 10e-7;

    public double calculate(double arg) {
        if (Double.isNaN(arg)) return  Double.NaN;
        if (arg < 0) return Double.NaN;
        if (arg == 0) return Double.NEGATIVE_INFINITY;
        double x = arg > 1 ? (1 / arg - 1) : arg - 1;
        double result = x;
        double cur = -x*x;
        int iter = 2;
        int sign = -1;
        while(Math.abs(sign * Math.pow(x, iter) / (iter + 1) + cur / iter) > tolerance / iter) {
            result += cur/iter++;
            sign = -sign;
            cur = sign * Math.pow(x, iter);
        }
        return arg > 1 ? -result : result;
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
