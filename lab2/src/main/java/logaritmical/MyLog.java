package logaritmical;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.PrintWriter;

public class MyLog {

    public MyLog(MyLn ln) {
        this.ln = ln;
    }

    public MyLog(double tolerance) {
        ln = new MyLn(tolerance);
    }

    public MyLog() {
        ln = new MyLn(0.01);
    }

    private final MyLn ln;

    public double ln(double x) {
        return ln.calculate(x);
    }

    public double log(double arg, double base) {
        return ln(arg) / ln(base);
    }

    public double getPrecision() {
        return ln.getPrecision();
    }

    public void writeCSV(double x, PrintWriter out) {
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            printer.printRecord(x, ln(x), log(x, 2), log(x, 3), log(x, 10));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
