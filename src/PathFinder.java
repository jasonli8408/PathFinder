import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class PathFinder {
    private PriorityQueue<Node> closedList = new PriorityQueue<>();
    private PriorityQueue<Node> openList = new PriorityQueue<>();
    private Node startNode;
    private Node endNode;
    private Grid grid;
    private List<Node> path;
    private Boolean hasSolution;



    public PathFinder(Node start, Node end, int row, int col) {
        startNode = start;
        endNode = end;
        closedList = new PriorityQueue<>();
        openList = new PriorityQueue<>();
        startNode.h = startNode.calcH(endNode);
        startNode.g = 0;
        startNode.f = start.calcF();

        openList.add(start);
        grid = new Grid(row, col);
        path = new LinkedList<>();
        hasSolution = false;
    }

    public Grid getGrid() {
        return grid;
    }

    public Node aStar() {
        while (!openList.isEmpty()) {
            Node current  = openList.poll();
            if (current.equals(endNode)) {
                return current;
            }
            closedList.add(current);
            openList.remove(current);


            //find all the children! and assign it to the arraylistsw
            List<Node> children = findChildren(current);
            for (Node child : children) {
                double tempG;
                if (Math.abs(current.getX() - child.getX()) == 1 && Math.abs(current.getY() - child.getY()) == 1) {
                    tempG = current.g + 1.4;
                } else {
                    tempG = current.g + 1;
                }

                if (!openList.contains(child) && !closedList.contains(child)) {

                    child.parent = current;
                    child.g = tempG;
                    child.f = child.g + child.calcH(endNode);
                    openList.add(child);

                } else  { //
                    if (openList.contains(child)) {
                        if (tempG < child.g) {
                            child.g = tempG;
                            child.parent = current;
                            child.f = child.g + child.calcH(endNode);

                        }
                        if (closedList.contains(child)) {
                            if (tempG < child.g) {
                                closedList.remove(child);
                                openList.add(child);
                                child.g = tempG;
                                child.parent = current;
                                child.f = child.g + child.calcH(endNode);
                            }
                        }
                    }
                }


            }


        }
        hasSolution = false;
        return null;

    }

    public List<Node> findPath(Node end) {
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
            children.add(addChildren(1, y - 1));
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
