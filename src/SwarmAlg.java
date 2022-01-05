import java.util.*;

public class SwarmAlg extends Observable {
    private final PriorityQueue<Node> unvisitedStart;
    private final PriorityQueue<Node> visitedStart;
    private final PriorityQueue<Node> unvisitedEnd;
    private final PriorityQueue<Node> visitedEnd;
    private final Node endNode;

    private static final double DEFAULT_DISTANCE = Double.MAX_VALUE;
    private final Grid grid;
    private boolean hasSolution;
    private boolean terminate;
    private List<Node> path;
    public static final String UNVISITED = "unvisited";
    public static final String VISITED = "visited";



    public SwarmAlg(Node start, Node endNode,  int row, int col) {
        path = new LinkedList<>();
        hasSolution = false;
        unvisitedStart = new PriorityQueue<>();
        visitedStart = new PriorityQueue<>();
        unvisitedEnd = new PriorityQueue<>();
        visitedEnd = new PriorityQueue<>();
        start.f = 0;

        grid = new Grid(row, col);
        this.endNode = endNode;
        unvisitedStart.add(start);
        unvisitedEnd.add(endNode);

        for (int i = 0 ; i < grid.getCols() ; i++) {
            for (int j = 0 ; j < grid.getRows() ; j++) {
                Node node = grid.getNode(j, i);
                if (node != start || node != endNode) {
                    node.f = DEFAULT_DISTANCE;
                }
            }
        }
    }

    public Node[] findIntersection() {

        while (!unvisitedStart.isEmpty()) {
            Node curr1 = unvisitedStart.poll(); // this is when you actually step on the node
            Node curr2 = unvisitedEnd.poll();
            visitedStart.add(curr1);
            visitedEnd.add(curr2);
            setChanged();
            notifyObservers(VISITED);
            for (Node node: visitedStart){
                for (Node node2: visitedEnd) {
                    if (isNeighbour(node, node2)){
                        return new Node[]{node, node2};
                    }
                }
            }


            List<Node> children1 = findChildren(curr1);
            int len1 = children1.size();
            List<Node> children2 = findChildren(curr2);
            children1.addAll(children2);
            int count = 0;

            for (Node neighbor : children1) {
                count += 1;
                if (neighbor != null) {
                    double tempf;
                    if (count <= len1) {
                        if (!visitedStart.contains(neighbor)) { //you can not step on a node that is already visited
                            if (Math.abs(curr1.getX() - neighbor.getX()) == 1 && Math.abs(curr1.getY() - neighbor.getY()) == 1) {
                                tempf = curr1.f + 1.4;
                            } else {
                                tempf = curr1.f + 1;
                            }
                            if (tempf < neighbor.f) { // checking if temp f is smaller than the already determeined f value
                                neighbor.f = tempf;
                                neighbor.parent = curr1;
                                if (unvisitedStart.contains(neighbor)) {
                                    unvisitedStart.remove(neighbor);
                                }
                                unvisitedStart.add(neighbor);
                                setChanged();
                                notifyObservers(UNVISITED);
                        }
                        }
                    } else {
                        if (!visitedEnd.contains(neighbor)) {
                            if (Math.abs(curr2.getX() - neighbor.getX()) == 1 && Math.abs(curr2.getY() - neighbor.getY()) == 1) {
                                tempf = curr2.f + 1.4;
                            } else {
                                tempf = curr2.f + 1;
                            }
                            if (tempf < neighbor.f) { // checking if temp f is smaller than the already determeined f value
                                neighbor.f = tempf;
                                neighbor.parent = curr2;
                                if (unvisitedEnd.contains(neighbor)) {
                                    unvisitedEnd.remove(neighbor);
                                }
                                unvisitedEnd.add(neighbor);
                                setChanged();
                                notifyObservers(UNVISITED);
                        }
                    }
                    }

                }
            }



        }
        return null;
    }

    private int distance;
    private boolean termination(Node node1, Node node2){

        return terminate;
    }



    public List<Node> findPath(Node[] nodes) {
        Node node1 = nodes[0];
        Node node2 = nodes[1];
        findIndividualPath(node1);
        return findIndividualPath(node2);
    }

    private List<Node> findIndividualPath(Node node){
        if (!hasSolution) {
            hasSolution = false;

        }
        //since each node only has ONE parent, we can simply traverse back to the starting point
        if (node == null) {
            return path;
        }
        path.add(node);
        Node parent = node.parent;
        findIndividualPath(parent);
        return path;
    }


    public PriorityQueue<Node> getUnvisitedStart() {
        return unvisitedStart;
    }

    public PriorityQueue<Node> getVisitedStart() {
        return visitedStart;
    }

    public PriorityQueue<Node> getUnvisitedEnd() {
        return unvisitedEnd;
    }

    public PriorityQueue<Node> getVisitedEnd() {
        return visitedEnd;
    }

    private boolean isNeighbour(Node node1, Node node2){
        List<Node> lst = findChildren(node1);
        if (!node2.isValidNode()) {
            return false;
        }
        for (Node node: lst){
            if (node.isValidNode()) {
                if (node.equals(node2)) {
                    return true;
                }
            }
        }
        return false;
    }


    public List<Node> findChildren(Node node) {
        ArrayList<Node> children = new ArrayList<>();
        int rows = grid.getRows();
        int cols = grid.getCols();
        int x = node.getX();
        int y = node.getY();

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
            children.add(addChildren(rows - 2, y - 1));
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
        if (grid.isValidNode(x, y)) {
            return grid.getNode(x, y);
        } else{
            return null;
        }
    }


    public Grid getGrid() {
        return grid;
    }
}
