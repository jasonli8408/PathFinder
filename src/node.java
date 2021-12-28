import com.sun.javafx.geom.Edge;

import java.util.ArrayList;
import java.util.List;

//this class represents a node in the graph
public class node implements Comparable<node>{
    //each node has a parent

    private node parent;

    //g(x) --> absolute distance moving from one node to another
    //f(x) --> full estimation of cost
    //h(x) --> from n to target end point

    //each node can also be a parents, so it has a list of edges
    private List<node> children;
    private double f;
    private double g;
    private double h;
    int x;
    int y;

    public node(double h, int x, int y) {
        this.x = x;
        this.y = y;
        this.h = h;
        parent = null;
        g = 0;
        f = this.h;
        children = new ArrayList<>();
    }

    @Override
    public int compareTo(node other) {
       if (this.f > other.f) {
           return 1;
       } else {
           return this.f < other.f ? - 1: 0;
       }
    }

    public void addChildren(node node) {
        if (!children.contains(node)) {
            children.add(node);
        }
    }

}
