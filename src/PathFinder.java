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
    }

    public Node aStar() {
        return null;
    }



}
