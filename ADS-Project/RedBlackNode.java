/**
 * A node class for a red-black tree implementation of rides.
 */
public class RedBlackNode extends Ride {
    // Null external black node.
    public static final RedBlackNode NIL = new RedBlackNode(-1, -1, -1);

    // Left, Right and Parent pointers.
    private RedBlackNode left, right, parent;

    // Color of the node. Red or Black.
    private NodeColor color;

    // Corresponding heap node reference.
    private HeapNode heapNodeReference;

    /**
     * Constructs a new RedBlackNode with the specified ride details.
     * @param rideNumber the number of the ride
     * @param rideCost the cost of the ride
     * @param tripDuration the duration of the ride
     */
    public RedBlackNode(int rideNumber, int rideCost, int tripDuration) {
        super(rideNumber, rideCost, tripDuration);
        this.setLeft(NIL);
        this.setRight(NIL);
        this.setParent(NIL);

        // Set RED color to all the nodes except NIL node, by default.
        this.setColor(rideNumber == -1 ? NodeColor.BLACK : NodeColor.RED);
    }

    /**
     * Gets the color of the node.
     * @return the color of the node
     */
    public NodeColor getColor() {
        return color;
    }

    /**
     * Sets the color of the node.
     * @param color the color to set
     */
    public void setColor(NodeColor color) {
        this.color = color;
    }

    /**
     * Gets the corresponding heap node reference.
     * @return the heap node reference
     */
    public HeapNode getHeapNodeReference() {
        return heapNodeReference;
    }

    /**
     * Sets the corresponding heap node reference.
     * @param heapNodeReference the heap node reference to set
     */
    public void setHeapNodeReference(HeapNode heapNodeReference) {
        this.heapNodeReference = heapNodeReference;
    }

    /**
     * Gets the left child of the node.
     * @return the left child
     */
    public RedBlackNode getLeft() {
        return left;
    }

    /**
     * Sets the left child of the node.
     * @param left the left child to set
     */
    public void setLeft(RedBlackNode left) {
        this.left = left;
    }

    /**
     * Gets the right child of the node.
     * @return the right child
     */
    public RedBlackNode getRight() {
        return right;
    }

    /**
     * Sets the right child of the node.
     * @param right the right child to set
     */
    public void setRight(RedBlackNode right) {
        this.right = right;
    }

    /**
     * Gets the parent of the node.
     * @return the parent
     */
    public RedBlackNode getParent() {
        return parent;
    }

    /**
     * Sets the parent of the node.
     * @param parent the parent to set
     */
    public void setParent(RedBlackNode parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "(" + this.getrideNumber() + "," + this.getrideCost() + "," + this.gettripDuration() + ")";
    }
}    
       
