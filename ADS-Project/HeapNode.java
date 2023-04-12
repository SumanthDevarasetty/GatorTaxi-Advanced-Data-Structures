
public class HeapNode extends Ride {
    // A pointer to the corresponding Red-Black tree node.
    private RedBlackNode rbtNodeReference;

    // Constructor.
    public HeapNode(int rideNumber, int rideCost, int tripDuration, RedBlackNode rbtNode) {
        super(rideNumber, rideCost, tripDuration);
        this.setRbtNodeReference(rbtNode);
    }

    public RedBlackNode getRbtNodeReference() {
        return rbtNodeReference;
    }

    private void setRbtNodeReference(RedBlackNode rbtNode) {
        this.rbtNodeReference = rbtNode;
    }
}