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
        startNode.f = start.calcF();
        startNode.g = start.calcG();
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


            //find all the children! and assign it to the arraylist
            List<Node> children = findChildren(m);
            for (Node child : children) {

            }


        }
        return null;

    }


    private List<Node> findChildren(Node node) {
        ArrayList<Node> children = new ArrayList<>();
        int rows = grid.getRows();
        int cols = grid.getCols();
        if ((1 <= node.getX() <= rows - 1)) && (1 <= node.getY() <= cols - 1)){

        }
        return children;
    }



}
