import java.util.*;

public class BreadthFirst extends Observable {
    //we use the priority queue data structure since in java its build upon min heap, and we could find the node with the
    //smallest f(x) value faster
    private final PriorityQueue<Node> BFSvisited; //nodes that are the options to go through
    private final Node startNode;
    private final Node endNode;
    //grid used to represent the structure of graph-like connecting the nodes together
    private final Grid grid;
    //represents the path after A* is complete
    private final List<Node> path;
    //if A* doesn't find solution and the blocks have blocked every path this stays false
    public Boolean hasSolution;
    //these strings are used as argument parameters for our observable function: list changed which sends message to GUI (back-end to front-end)
    public static final String CLOSED = "CLOSED"; //closed list changed
    public static final String OPEN = "OPEN"; //open list changed

    public BreadthFirst(Node start, Node end, int row, int col) {
        startNode = start;
        endNode = end;
        BFSvisited = new PriorityQueue<>();
        BFSvisited.add(start); //openList adds the start node in the beginning for the A* algorithm
        grid = new Grid(row, col); //we create a grid according to the inputted rows and columns
        path = new LinkedList<>(); //the path is a linkedList since we don't know how many nodes are in our path, and we don't do any searches
    }

    public Grid getGrid() { //gets the grid in back-end
        return grid;
    }

    public Node BFS() {
        while (!BFSvisited.isEmpty()) { //while the open list is not empty, and we have options to step on
            Node current  = BFSvisited.poll(); //we get the smallest f valued node, best option
            setChanged();
            notifyObservers("v");
            if (current.equals(endNode)) { //it already found the solution
                hasSolution = true;
                return current;
            }

            List<Node> children = findChildren(current);
            for (Node child : children) {
                if (child != null && !BFSvisited.contains(child)) {
                    child.parent = current;
                    BFSvisited.add(child);
                }
            }
        }
        hasSolution = false; //if its empty we have been through everything and still have not found a solution
        return null;
    }

    public List<Node> findPath(Node end) {
        //since each node only has ONE parent, we can simply traverse back to the starting point to get the shortest path
        if (end == null) {
            return path;
        }
        path.add(end);
        Node parent = end.parent;
        findPath(parent);
        return path;
    }

    public List<Node> findChildren(Node node) {
        ArrayList<Node> children = new ArrayList<>();
        int rows = grid.getRows();
        int cols = grid.getCols();
        int x = node.getX();
        int y = node.getY();
        //gets the child and considers the special cases, corner nodes
        if (1 <= node.getX() && node.getX() <= rows - 2 && 1 <= node.getY() && node.getY() <= cols - 2) {
            children.add(addChildren(x - 1, y - 1));
            children.add(addChildren(x - 1, y));
            children.add(addChildren(x - 1, y + 1));
            children.add(addChildren(x, y - 1));
            children.add(addChildren(x, y + 1));
            children.add(addChildren(x + 1, y - 1));
            children.add(addChildren(x + 1, y));
            children.add(addChildren(x + 1, y + 1));
        } else if (node.getX() == 0 && node.getY() == 0) {
            children.add(addChildren(0, 1));
            children.add(addChildren(1, 1));
            children.add(addChildren(1, 0));
        } else if (node.getX() == 0 && node.getY() == cols - 1) {
            children.add(addChildren(0, cols - 2));
            children.add(addChildren(1, cols - 2));
            children.add(addChildren(1, cols - 1));
        } else if (node.getX() == rows - 1 && node.getY() == 0) {
            children.add(addChildren(rows - 2, 0));
            children.add(addChildren(rows - 2, 1));
            children.add(addChildren(rows - 1, 1));
        } else if (node.getX() == rows - 1 && node.getY() == cols - 1) {
            children.add(addChildren(rows - 2, cols - 1));
            children.add(addChildren(rows - 2, cols - 2));
            children.add(addChildren(rows - 1, cols - 2));
        } else if (node.getX() == 0) {
            children.add(addChildren(0, y - 1));
            children.add(addChildren(0, y + 1));
            children.add(addChildren(1, y - 1));
            children.add(addChildren(1, y));
            children.add(addChildren(1, y + 1));
        } else if (node.getX() == rows - 1) {
            children.add(addChildren(rows - 1, y - 1));
            children.add(addChildren(rows - 1, y + 1));
            children.add(addChildren(rows - 2, y - 1));
            children.add(addChildren(rows - 2, y));
            children.add(addChildren(rows - 2, y + 1));
        } else if (node.getY() == 0) {
            children.add(addChildren(x - 1, 0));
            children.add(addChildren(x + 1, 0));
            children.add(addChildren(x - 1, 1));
            children.add(addChildren(x, 1));
            children.add(addChildren(x + 1, 1));
        } else if (node.getY() == cols - 1) {
            children.add(addChildren(x - 1, cols - 1));
            children.add(addChildren(x + 1, cols - 1));
            children.add(addChildren(x - 1, cols - 2));
            children.add(addChildren(x, cols - 2));
            children.add(addChildren(x - 1, cols - 2));
        }
        return children;
    }

    private Node addChildren(int x, int y) {
        //if the node is a block we don't return so find children wouldn't have block nodes in the list
        if (grid.isValidNode(x, y)) {
            return grid.getNode(x, y);
        } else{
            return null;
        }
    }




}
