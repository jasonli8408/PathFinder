import java.util.ArrayList;
import java.util.List;

//this class represents a Node in the graph
public class Node extends Coordinate implements Comparable<Node>{
    //each Node has a parent

    private Node parent;

    //g(x) --> absolute distance moving from one Node to another
    //f(x) --> full estimation of cost
    //h(x) --> from n to target end point

    //each Node can also be a parents, so it has a list of edges
    private List<Node> children;
    private double f;
    private double g;
    private double h;
    int x;
    int y;

    public Node(double h, int x, int y) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.h = h;
        parent = null;
        g = 0;
        f = this.h;
        children = new ArrayList<>();
    }

    @Override
    public int compareTo(Node other) {
       if (this.f > other.f) {
           return 1;
       } else {
           return this.f < other.f ? - 1: 0;
       }
    }

    public void addChildren(Node node) {
        if (!children.contains(node)) {
            children.add(node);
        }
    }

}
