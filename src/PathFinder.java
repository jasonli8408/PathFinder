import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PathFinder {
    private PriorityQueue<Node> closedList = new PriorityQueue<>();
    private PriorityQueue<Node> openList = new PriorityQueue<>();
    private Node startNode;
    private Node endNode;
    private Grid grid;



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
    }

    public Node aStar() {
        while (!openList.isEmpty()) {
            Node m  = openList.poll();
            if (m.equals(endNode)) {
                return m;
            }
            closedList.add(m);
            openList.remove(m);


            //find all the children! and assign it to the arraylists
            List<Node> children = findChildren(m);
            for (Node child : children) {
                if (Math.abs(m.getX() - child.getX()) == 1 && Math.abs(m.getY() - child.getY()) == 1) {
                    child.g = m.g + 1.4;
                } else {
                    child.g = m.g + 1;
                }

                if (!openList.contains(child) && !closedList.contains(child)) {

                    child.f = child.g + child.calcH(endNode);
                    openList.add(child);

                } else  {



                }


            }


        }
        return null;

    }

    private List<Node> findChildren(Node node) {
        ArrayList<Node> children = new ArrayList<>();
        int rows = grid.getRows();
        int cols = grid.getCols();
        int x = node.getX();
        int y = node.getY();

        if (1 <= node.getY() && node.getY() <= rows - 1 && 1 <= node.getX() && node.getX() <= cols) {
            children.add(addChildren(x - 1, y - 1));
            children.add(addChildren(x - 1, y));
            children.add(addChildren(x - 1, y + 1));
            children.add(addChildren(x, y - 1));
            children.add(addChildren(x, y + 1));
            children.add(addChildren(x + 1, y - 1));
            children.add(addChildren(x + 1, y));
            children.add(addChildren(x + 1, y + 1));
        } else if (node.getX() == 1 && node.getY() == 1) {
            children.add(addChildren(1, 2));
            children.add(addChildren(2, 2));
            children.add(addChildren(2, 1));
        } else if (node.getX() == 1 && node.getY() == rows) {
            children.add(addChildren(1, rows - 1));
            children.add(addChildren(2, rows - 1));
            children.add(addChildren(2, rows));
        } else if (node.getX() == cols && node.getY() == 1) {
            children.add(addChildren(cols - 1, 1));
            children.add(addChildren(cols - 1, 2));
            children.add(addChildren(cols, 2));
        } else if (node.getX() == cols && node.getY() == rows) {
            children.add(addChildren(cols - 1, rows));
            children.add(addChildren(cols - 1,  rows- 1));
            children.add(addChildren(cols, rows - 1));
        } else if (node.getX() == 1) {
            children.add(addChildren(1, rows - 1));
            children.add(addChildren(1, rows + 1));
            children.add(addChildren(2, rows - 1));
            children.add(addChildren(2, rows));
            children.add(addChildren(2, rows + 1));
        } else if (node.getX() == cols) {
            children.add(addChildren(cols, rows - 1));
            children.add(addChildren(cols, rows + 1));
            children.add(addChildren(cols - 1, rows - 1));
            children.add(addChildren(cols - 1, rows));
            children.add(addChildren(cols - 1, rows + 1));
        } else if (node.getY() == 1) {
            children.add(addChildren(cols - 1, 1));
            children.add(addChildren(cols + 1, 1));
            children.add(addChildren(cols - 1, 2));
            children.add(addChildren(cols, 2));
            children.add(addChildren(cols + 1, 2));
        } else if (node.getY() == rows) {
            children.add(addChildren(cols - 1, rows));
            children.add(addChildren(cols + 1, rows));
            children.add(addChildren(cols - 1, rows - 1));
            children.add(addChildren(cols, rows - 1));
            children.add(addChildren(cols + 1, rows - 1));
        }

        return children;
    }

    private Node addChildren(int x, int y) {
        if (grid.isValidNode(y, x)) {
            return grid.getNode(y, x);
        } else{
            return null;
        }
    }


}
