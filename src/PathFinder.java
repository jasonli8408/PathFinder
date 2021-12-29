import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PathFinder {
    private PriorityQueue<Node> closedList = new PriorityQueue<>();
    private PriorityQueue<Node> openList = new PriorityQueue<>();
    private Node startNode;
    private Node endNode;



    public PathFinder(Node start, Node end) {
        startNode = start;
        endNode = end;
        closedList = new PriorityQueue<>();
        openList = new PriorityQueue<>();
        startNode.h = startNode.calcH(endNode);
        startNode.f = start.calcF();
        startNode.g = start.calcG();
        openList.add(start);
    }

    public Node aStar() {
        while (!openList.isEmpty()) {
            Node m  = openList.poll();
            if (m.equals(endNode)) {
                return m;
            }
            closedList.add(m);


            //find all the children! and assign it to the arraylist

        }


        return null;

    }


    private List<Node> findChildren(Node node) {
        ArrayList<Node> children = new ArrayList<>();
        if (node.getX()
        return children;
    }



}
