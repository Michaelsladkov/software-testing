import logaritmical.MyLog;
import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import trigonometrical.MyTrigonometry;

import java.io.IOException;
import java.io.PrintWriter;

public class TaskFunction {
    MyLog log;
    MyTrigonometry tr;

    @Getter
    double precision;

    public TaskFunction(double precision) {
        this.precision = precision;
        tr = new MyTrigonometry(precision);
        log = new MyLog(precision);
    }

    public TaskFunction(MyLog logModule, MyTrigonometry trigonometryModule) {
        log = logModule;
        tr = trigonometryModule;
        precision = Math.max(log.getPrecision(), tr.getPrecision());
    }

    public double calculate(double x) {
        if (x > 0) {
            return ((tr.tan(x) - tr.cos(x) * tr.sec(x)) / tr.cot(x)) +
                    (tr.sec(x) / tr.tan(x)) +
                    ((tr.sec(x) + tr.cos(x)) * (((tr.tan(x)) / tr.cot(x) * tr.sin(x))) / tr.tan(x));
        }
        return (((log.log(x, 10) * log.log(x, 2) - log.log(x, 2)) - log.log(x * x, 10)) / log.log(x, 2)) * log.log(x, 10);
    }

    public void writeCSV(double x, PrintWriter out) {
        double res = calculate(x);
        try{
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}