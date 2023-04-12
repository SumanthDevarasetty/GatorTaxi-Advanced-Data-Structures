import java.io.IOException;
import java.util.Objects;
import java.util.Queue;

public class RidesExecutor {
    private Queue<TestCase> testCases;
    private final int MAX_RIDES = 2000;
    private MinHeap minHeap;
    private RedBlackTree redBlackTree;

    public RidesExecutor(Queue<TestCase> testCases) {
        this.testCases = testCases;
        this.minHeap = new MinHeap(MAX_RIDES);
        this.redBlackTree = new RedBlackTree();
    }

    public void build() {
        while (!testCases.isEmpty()) {
            TestCase testCase = testCases.peek();
            switch (testCase.getTestCommand()) {
                case INSERT:
                    try {
                        insertBuilding(testCase.getRideNumber(), testCase.getrideCost(), testCase.getTripDuration());
                    } catch (Exception exception) {
                        OutputParser.addErrorMessage(exception.getMessage());
                        finish();
                    }
                    break;
                case PRINT:
                    OutputParser.addRideTuple(redBlackTree.search(testCase.getStartRideNumber()));
                    break;
                case GETNEXTRIDE:
                    getNextNode();
                    break;
                case PRINTBTW:
                    OutputParser.addBetweenRides(redBlackTree.searchInRange(testCase.getStartRideNumber(), testCase.getEndRideNumber()));
                    break;
                case CANCEL:
                    cancelRide(testCase.getRideNumber());
                    break;
                case UPDATE:
                    updateRide(testCase);
                    break;
            }
            testCases.remove();
        }
        finish();
    }

    private void insertBuilding(int rideNumber, int rideCost, int tripDuration) {
        RedBlackNode rbNode = new RedBlackNode(rideNumber, rideCost, tripDuration);
        RedBlackNode exist = redBlackTree.search(rideNumber);
        if (Objects.isNull(exist)) {
            HeapNode heapNode = new HeapNode(rideNumber, rideCost, tripDuration, rbNode);
            rbNode.setHeapNodeReference(heapNode);
            redBlackTree.insert(rbNode);
            minHeap.insert(heapNode);
        } else {
            throw new IllegalArgumentException("Duplicate RideNumber");
        }
    }

    private void getNextNode() {
        HeapNode min = minHeap.extractMin();
        if (min != null) {
            OutputParser.addRideTuple(min.getRbtNodeReference());
            redBlackTree.delete(min.getRbtNodeReference());
        } else {
            OutputParser.appendString("No active ride requests");
        }
    }

    private void cancelRide(int rideNumber) {
        RedBlackNode exist = redBlackTree.search(rideNumber);
        if (exist != null) {
            RedBlackNode node = minHeap.delete(rideNumber);
            redBlackTree.delete(node);
        }
    }

    private void updateRide(TestCase testCase) {
        RedBlackNode node = redBlackTree.search(testCase.getRideNumber());
        if (testCase.getTripDuration() <= node.gettripDuration()) {
            HeapNode heapNode = node.getHeapNodeReference();
            node.settripDuration(testCase.getTripDuration());
            heapNode.settripDuration(testCase.getTripDuration());
        } else if (node.gettripDuration() < testCase.getTripDuration() && testCase.getTripDuration() <= 2 * node.gettripDuration()) {
            redBlackTree.delete(node);
            minHeap.delete(node.getrideNumber());
            insertBuilding(node.getrideNumber(), node.getrideCost() + 10, testCase.getTripDuration());
        } else if (testCase.getTripDuration() > 2 * node.gettripDuration()) {
            redBlackTree.delete(node);
            minHeap.delete(node.getrideNumber());
        }
    }
    // Finish execution. Print the output to the file.
    private void finish() {
        try {
            OutputParser.print();
            System.exit(0);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
 
}    