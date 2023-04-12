// A parser for reading test case data from the input and creating TestCase object from it.

public class InputParser {
    public static TestCase getParsedTestCase(String input) {
        TestCase testCase = new TestCase();
        testCase.setTestCommand(getTestCommand(input));

        switch (testCase.getTestCommand()) {
            case INSERT:
                String[] insertArgs = input.split("[\\(\\),]");
                testCase.setRideNumber(Integer.parseInt(insertArgs[1]));
                testCase.setRideCost(Integer.parseInt(insertArgs[2]));
                testCase.setTripDuration(Integer.parseInt(insertArgs[3]));
                break;
            case PRINT:
                String[] printArgs = input.split("[\\(\\)]");
                testCase.setStartRideNumber(Integer.parseInt(printArgs[1]));
                break;
            case GETNEXTRIDE:
                break;
            case PRINTBTW:
                String[] printBtwArgs = input.split("[\\(\\),]");
                testCase.setStartRideNumber(Integer.parseInt(printBtwArgs[1]));
                testCase.setEndRideNumber(Integer.parseInt(printBtwArgs[2]));
                break;
            case CANCEL:
                String[] cancelArgs = input.split("[\\(\\)]");
                testCase.setRideNumber(Integer.parseInt(cancelArgs[1]));
                break;
            case UPDATE:
                String[] updateArgs = input.split("[\\(\\),]");
                testCase.setRideNumber(Integer.parseInt(updateArgs[1]));
                testCase.setTripDuration(Integer.parseInt(updateArgs[2]));
                break;
        }

        return testCase;
    }

    private static TestCommand getTestCommand(String input) {
        if (input.startsWith("Insert")) {
            return TestCommand.INSERT;
        } else if (input.startsWith("Print")) {
            return input.contains(",") ? TestCommand.PRINTBTW : TestCommand.PRINT;
        } else if (input.startsWith("GetNextRide")) {
            return TestCommand.GETNEXTRIDE;
        } else if (input.startsWith("Cancel")) {
            return TestCommand.CANCEL;
        } else if (input.startsWith("Update")) {
            return TestCommand.UPDATE;
        } else {
            throw new IllegalArgumentException("Invalid input: " + input);
        }
    }
}






