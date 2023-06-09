// Min Heap data structure.
public class MinHeap {
    // Heap array containing Node objects.
    private HeapNode[] heap;

    // Max capacity for the heap array.
    private int capacity;

    // Number of currently inserted items into the heap array.
    private int size;

    // Index of the top-most item of the heap.
    private static final int TOP = 1;

    // Constructor.
    public MinHeap(int capacity) {
        this.capacity = capacity;
        initHeap(capacity);
    }

    // Initialize heap array with the given capacity.
    private void initHeap(int capacity) {
        this.heap = new HeapNode[capacity + 1];
        this.heap[0] = null;
        size++;
    }

    // Return true if the heap is empty, false otherwise.
    public boolean isEmpty() {
        return size == 1;
    }

    // Returns true if the given position has parent, false otherwise.
    private boolean hasParent(int position) {
        return position != 1;
    }

    // Returns true if the item at the given position is a leaf node, false otherwise.
    private boolean isLeaf(int position) {
        return (position >= size/2 && position <= size);
    }

    // Returns the index of the parent of the given position.
    private int getParentPosition(int position) {
        return (position / 2);
    }

    // Returns the index of the left child of the given position.
    private int getLeftChildPosition(int position) {
        return (position * 2);
    }

    // Returns the index of the right child of the given position.
    private int getRightChildPosition(int position) {
        return (position * 2) + 1;
    }

    // Swap two items in the heap array.
    private void swap(int i, int j) {
        HeapNode temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Inserts node into heap array.
    public void insert(HeapNode node) {
        if(size > capacity) {
            throw new IllegalStateException("Error: Capacity Exceeded. Cannot insert more than " + capacity + " items.");
        }
        
        heap[size] = node;
        heapifyUp(size);
    }

    // Rearranges the heap array as per heap property whenever a new item is inserted.
    private void heapifyUp(int position) {
        while(hasParent(position) && 
                heap[position].getrideCost() <= heap[getParentPosition(position)].getrideCost()) {
            if(heap[position].getrideCost() < heap[getParentPosition(position)].getrideCost() || 
                heap[position].gettripDuration() < heap[getParentPosition(position)].gettripDuration()) {
                    swap(position, getParentPosition(position));
                    position = getParentPosition(position);
            } 
            else {
                break;
            }
        }

        size++;
    }

    // Removes and returns the minimum item from the heap.
    public HeapNode extractMin() {
        if(isEmpty()) {
            return null;
        }

        HeapNode item = heap[TOP];
        // Copies the last heap item at top, and then heapify it down.
        heap[TOP] = heap[size - 1];
        heapifyDown(TOP);
        heap[size - 1] = null;
        size--;
        return item;
    }

    // finds the heap index that contains given rideNumber
    private int find(int rideNumber){
        for(int i=1; i <= size; i++ ){
            if(heap[i].getrideNumber() == rideNumber)
                return i;
        }
        return -1;
    }

    // deletes the heap node with given rideNumber
    public RedBlackNode delete(int rideNumber){
        int index = find(rideNumber);
        if(index ==-1 )
            return null ;
        RedBlackNode rbnode = heap[index].getRbtNodeReference();    
        heap[index] = heap[size-1];
        size--;
        heapifyDown(index);
        return rbnode;    
    }

    // Set the node at given position to it's appropriate index by recursively performing heapify operation.
    private void heapifyDown(int position) {
        // Break the recursion when the item is leaf node.
        if(isLeaf(position)) return;

        // Left and Right child indexes.
        int left = getLeftChildPosition(position);
        int right = getRightChildPosition(position);

        // Check which child has smaller value. Tie is broken by comparing building number if executed time is same.
        boolean isLeftSmaller = (heap[left].getrideCost() != heap[right].getrideCost()) ? 
                                heap[left].getrideCost() < heap[right].getrideCost() : 
                                heap[left].gettripDuration() < heap[right].gettripDuration();

        if(isLeftSmaller && heap[position].getrideCost() >= heap[left].getrideCost() && 
           (heap[position].getrideCost() > heap[left].getrideCost() || 
           heap[position].gettripDuration() > heap[left].gettripDuration())) {
            swap(position, left);
            heapifyDown(left);
        } 
        else if(heap[position].getrideCost() >= heap[right].getrideCost() && 
                (heap[position].getrideCost() > heap[right].getrideCost() || 
                heap[position].gettripDuration() > heap[right].gettripDuration())) {
            swap(position, right);
            heapifyDown(right);
        }
    }
}