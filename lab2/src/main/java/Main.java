import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        TaskFunction taskFunction = new TaskFunction(0.001);
        String outPath = "TaskFuncOut.csv";
        PrintWriter CSVOut;
        try {
            CSVOut = new PrintWriter(outPath);
            for (double x = -10; x < 10; x += 0.001) {
                taskFunction.writeCSV(x, CSVOut);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Failed to open out file");
            e.printStackTrace();
        }
    }
}
