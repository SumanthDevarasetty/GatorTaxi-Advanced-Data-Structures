import java.io.*;
import java.util.*;

public class gatorTaxi {
    // Store test cases in a queue
    private static Queue<TestCase> testCases;

    public static void main(String[] args) throws IOException {
        // Read input file name from command-line arguments
        String inputFileName = args[0];

        // Parse test cases from input file and add them to the queue
        testCases = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(inputFileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                testCases.add(InputParser.getParsedTestCase(line));
            }
        }

        // Execute the test cases
        RidesExecutor ridesExecutor = new RidesExecutor(testCases);
        ridesExecutor.build();
    }
}




