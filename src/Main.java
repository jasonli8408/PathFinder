import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Node startNode = new Node(2,5);
        Node endNode = new Node(8,7);
        DijkstraAlg d = new DijkstraAlg(startNode, endNode, 10,10);


         d.findEndNode();
         Set<Node> path = d.findPath(d.getEndNode());
         for (Node next : path) {
             System.out.println(next);
         }



//        PathFinder pathFinder = new PathFinder(startNode, endNode, 10,10);
//        Node end = pathFinder.aStar();
//        List<Node> path2 = pathFinder.findPath(end);
//        for (Node next : path2) {
//            System.out.println(next);
//        }
//        new GUI();


    }
}
