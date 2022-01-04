import java.util.*;
import java.util.concurrent.TimeUnit;

import java.util.*;
//the class that has the functions and algorithm that calculates the shortest path
public class PathFinder extends Observable {
    //we use the priority queue data structure since in java its build upon min heap, and we could find the node with the
    //smallest f(x) value faster
    private final PriorityQueue<Node> closedList; //nodes that have been already visited
    private final PriorityQueue<Node> openList; //nodes that are the options to go through
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


    public PathFinder(Node start, Node end, int row, int col) {
        startNode = start;
        endNode = end;
        closedList = new PriorityQueue<>();
        openList = new PriorityQueue<>();
        //we calculate all the f,g,h values for start node since startNode is used as the first input for A*
        startNode.h = startNode.calcH(endNode);//the distance from (current) startNode to endNode
        startNode.g = 0; //the distance between (starting node) current and starting node is 0
        startNode.f = start.calcF(); //calculates start nodes f value
        openList.add(start); //openList adds the start node in the beginning for the A* algorithm
        grid = new Grid(row, col); //we create a grid according to the inputted rows and columns
        path = new LinkedList<>(); //the path is a linkedList since we don't know how many nodes are in our path, and we don't do any searches
    }

    public Grid getGrid() { //gets the grid in back-end
        return grid;
    }

    public Node aStar() {
        while (!openList.isEmpty()) { //while the open list is not empty, and we have options to step on
            Node current  = openList.poll(); //we get the smallest f valued node, best option
            if (current.equals(endNode)) { //it already found the solution
                hasSolution = true;
                return current;
            }
            //else if the node is not equal to the end node: solution has not been found yet
            ListChanged(2, current); //add the node to closed since you just stepped on it
            ListChanged(1, current); //remove it from the open List since you are already on it and its not available
            //as an option any more
            //find all the children! and assign it to the children
            List<Node> children = findChildren(current);
            for (Node child : children) {
                if (child != null) {
                    //g value changes based on each path but h value doesn't:
                    //calculates the G value during this path iterative: the previous (parents) g value + 1 or 1.4 depending on where the child is
                    //as we go to child nodes from parent nodes we add the parents g value with the distance of child node and current node:
                    //dist(starting & current) + dis(current & child) = dis(starting & child)
                    double tempG; //this g value is temporary
                    if (Math.abs(current.getX() - child.getX()) == 1 && Math.abs(current.getY() - child.getY()) == 1) {
                        //if its diagonal we add the current g by 1.4 since we have moved away from the starting node by 1.4 unit rounded radical(1+1)
                        tempG = current.g + 1.4;
                    } else {
                        //the child is just one unit away from the current
                        tempG = current.g + 1;
                    }
                    if (!openList.contains(child) && !closedList.contains(child)) { //when we have never calculated anything for the node
                        child.parent = current;
                        child.g = tempG;
                        child.f = child.g + child.calcH(endNode);
                        ListChanged(0, child); //open list add: you have this child as an option for your next step
                    } else { //when the g value has been calculated before
                        if (openList.contains(child)) {
                            //if this G value is smaller than the one calculated by the other path we replace it
                            if (tempG < child.g) {
                                child.g = tempG;
                                //we changed the child parent since this path has smaller g value
                                child.parent = current;
                                child.f = child.g + child.calcH(endNode);
                            }
                            if (closedList.contains(child)) {
                                //if we have visited the node before and no matter if our current path includes it or not
                                //if the distance of (starting node and current node) of this path is shorter than the paths before
                                if (tempG < child.g) {
                                    //we remove the node from closed list
                                    ListChanged(3, child);
                                    //we add  it to open list since now it's an option that can be the next step
                                    ListChanged(0, child);
                                    //we update the old values since we found a better path
                                    child.g = tempG;
                                    child.parent = current;
                                    child.f = child.g + child.calcH(endNode);
                                }
                            }
                        }
                    }
                }
            }
        }
        hasSolution = false; //if its empty we have been through everything and still have not found a solution
        return null;
    }

    public List<Node> findPath(Node end) {
        if (!hasSolution) { //find path wouldn't calculate anything
            hasSolution = false;
        }
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
    //getters for closed and openList since we need them in gui
    public PriorityQueue<Node> getClosed() {
        return closedList;
    }
    public PriorityQueue<Node> getOpenList() {
        return openList;
    }

    public void ListChanged(int operation, Node child) {
        //does the closed list and open list operations based on operation input, and it also sends a message to update function
        //in gui to form an animation
        if (operation == 0) {
            openList.add(child);
            setChanged();
            notifyObservers(OPEN);
        } else if (operation == 1){
            openList.remove(child);
            setChanged();
            notifyObservers(OPEN);
        } else if (operation == 2){
            closedList.add(child);
            setChanged();
            notifyObservers(CLOSED);
        } else if (operation == 3){
            closedList.remove(child);
            setChanged();
            notifyObservers (CLOSED);
        }
    }
}




