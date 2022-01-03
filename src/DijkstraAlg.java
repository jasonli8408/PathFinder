import java.util.*;

public class DijkstraAlg extends Observable {
    private PriorityQueue<Node> unsettled;
    private PriorityQueue<Node> settled;
    private Node current;
    private Node start;
    private Node endNode;
    private static final double DEFAULT_DISTANCE = Double.MAX_VALUE;
    private Grid  grid;
    private boolean hasSolution;
    private Set<Node> path;



    public DijkstraAlg(Node start, Node endNode,  int row, int col) {
        path = new HashSet<>();
        hasSolution = false;
        unsettled = new PriorityQueue<>(Comparator.comparingDouble(a -> a.g));
        settled = new PriorityQueue<>();
        this.start = start;
        start.g = 0;
        current = start;
        grid = new Grid(row, col);
        this.endNode = endNode;
        unsettled.add(start);

        for (int i = 0 ; i < grid.getCols() ; i++) {
            for (int j = 0 ; j < grid.getRows() ; j++) {
                Node node = grid.getNode(j, i);
                if (node != start) {
                    node.g = DEFAULT_DISTANCE;
                }
            }
        }
    }

    public void findEndNode() {

        while (!unsettled.isEmpty()) {
            Node curr = unsettled.poll();



            List<Node> children = findChildren(curr);
            for (Node neighbor : children) {

                double tempG = Double.MAX_VALUE;
                if (neighbor != null) {

                    if (Math.abs(current.getX() - neighbor.getX()) == 1 && Math.abs(current.getY() - neighbor.getY()) == 1) {
                        tempG = current.g + 1.4;
                    } else {
                        tempG = current.g + 1;
                    }

                }

                if (tempG < neighbor.g) {
                    if (settled.contains(neighbor)) {
                        settled.remove(neighbor);
                        neighbor.g = tempG;
                        neighbor.parent = curr;
                        settled.add(neighbor);
                        unsettled.add(neighbor);
                    } else {
                        neighbor.g = tempG;
                        neighbor.parent = curr;
                        settled.add(neighbor);
                        unsettled.add(neighbor);
                    }


                    
                }


            }



        }
    }


    public Set<Node> findPath(Node end) {
        if (!hasSolution) {
            hasSolution = false;

        }
        //since each node only has ONE parent, we can simply traverse back to the starting point
        if (end == null) {
            return path;
        }
        path.add(end);
        Node parent = end.parent;
        findPath(parent);
        return path;
    }


    public Node getEndNode() {
        Node ans = null;
        for (Node node : settled) {
            if (node.equals(endNode)) {
                ans =  node;
            }
        }
        return ans;
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


}
