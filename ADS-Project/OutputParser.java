import java.io.*;
import java.util.*;

public class OutputParser {
    private static StringBuilder output = new StringBuilder();
    private static final String EMPTY_NODE_TUPLE = "(0,0,0)";
    private static final String OUTPUT_FILENAME = "output_file.txt";


    public static void addRideTuple(RedBlackNode rideNode) {
        String rideTuple = (rideNode != null) ? rideNode.toString() : EMPTY_NODE_TUPLE;
        output.append(String.format("%s%n", rideTuple));
    }

    public static void addBetweenRides(List<RedBlackNode> rideNodes) {
        if (rideNodes.isEmpty()) {
            output.append(String.format("%s%n", EMPTY_NODE_TUPLE));
        } else {
            for (RedBlackNode rideNode : rideNodes) {
                String rideTuple = (rideNode != null) ? rideNode.toString() : EMPTY_NODE_TUPLE;
                output.append(String.format("%s,", rideTuple));
            }
            output.setLength(output.length() - 1); // Remove extra comma
            output.append(String.format("%n"));
        }
    }

    public static void addErrorMessage(String error) {
        output.append(String.format("%s%n", error));
    }

    public static void printN(HeapNode node) {
        output.append(node.toString());
    }

    public static void appendString(String s) {
        output.append(String.format("%s%n", s));
    }

    public static void print() throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT_FILENAME))) {
            bufferedWriter.write(output.toString().trim());
        }
    }
}
