package trigonometrical;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.PrintWriter;

public class MyTrigonometry {
    private final MySin sin;

    public MyTrigonometry(MySin sin) {
        this.sin = sin;
    }

    public MyTrigonometry(double tolerance) {
        sin = new MySin(tolerance);
    }

    public double getPrecision() {
        return sin.getPrecision();
    }

    public double sin(double x) {
        return sin.calculate(x);
    }

    public double cos(double x) {
        return sin(Math.PI / 2 + x);
    }

    public double tan(double arg) {
        double x = arg - Math.PI * Math.floor((arg + Math.PI / 2) / Math.PI);
        return (x > 0) ? -sin(-x) / cos(-x) : sin(x) / cos(x);
    }

    public double sec(double x) {
        return 1 / cos(-Math.abs(x));
    }

    public double cot(double x) {
        return 1 / tan(x);
    }

    public void writeCSV(double x, PrintWriter out) {
        try {
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            printer.printRecord(x, sin(x), cos(x), tan(x), sec(x), cot(x));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

