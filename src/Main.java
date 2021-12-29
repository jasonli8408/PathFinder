import java.util.List;

public class Main {

    public static void main(String[] args) {
        Node startNode = new Node(0,0);
        Node endNode = new Node(6,6);
        PathFinder pathFinder = new PathFinder(startNode, endNode, 7,7);
        //System.out.println(pathFinder.findChildren(startNode));
        //System.out.println(pathFinder.findChildren(endNode));

        Node end = pathFinder.aStar();
        List<Node> path = pathFinder.findPath(end);

        for (Node node : path) {
            System.out.println(node);
        }

    }
}
