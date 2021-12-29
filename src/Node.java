import com.sun.javafx.geom.Edge;

import java.util.ArrayList;
import java.util.List;

//this class represents a node in the graph
public class Node implements Comparable<Node>{
    //each node has a parent

    private Node parent;

    //g(x) --> absolute distance moving from one node to another
    //f(x) --> full estimation of cost
    //h(x) --> from n to target end point

    //each node can also be a parents, so it has a list of edges
    private List<Node> children;
    private double f;
    private double g;
    private double h;
    private Boolean isNode;
    private int x;
    private int y;

    public Node(double h, int x, int y) {
        this.x = x;
        this.y = y;
        this.h = h;
        parent = null;
        g = 0;
        f = this.h;
        children = new ArrayList<>();
        isNode = true;
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

    public boolean checkIsNode() {
        return isNode;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }




}
