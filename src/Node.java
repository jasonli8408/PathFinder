import com.sun.javafx.geom.Edge;

import java.util.ArrayList;
import java.util.List;

//this class represents a node in the graph
public class Node implements Comparable<Node>{
    //each node has a parent

    //g(x) --> absolute distance moving from one node to another
    //f(x) --> full estimation of cost
    //h(x) --> from n to target end point

    //each node can also be a parents, so it has a list of edges

    private double f;
    private double g;
    private double h;
    private Boolean isNode; //after the input we understand if the node can be used or not (might be an obstacle)
    private int x; //for the coordinate
    private int y;

    public Node(int x1, int y1) {

        //values that are constant
        x = x1;
        y = y1;

        //values that are going to get calculated
        h = -1; //-1 means we haven't calculated the value since h, f,g are not constant
        g = -1;
        f = -1;

        //values that change based on input
        isNode = true; //since from the beginning there are no obstacle its assumed every node is available
    }


    @Override
    public int compareTo(Node other) {
       if (this.f > other.f) {
           return 1;
       } else {
           return this.f < other.f ? - 1: 0;
       }
    }

    //getters since everything is private

    public double getF() { //getF for comparisons
        return f;
    }

    public double getG() { //getG to calculate F
        return g;
    }

    public double getH() { //getH to calculate F
        return h;
    }

    public Boolean getNode() { //check if node is an obstacle and if it is, don't calculate F
        return isNode;
    }

    public int getX() { //get coordinates if its needed
        return x;
    }

    public int getY() {
        return y;
    }

    //setters F,G,H are constantly changing and isNode changes based on input


    public void calcF(double g1, double h1) {
        this.f = g1 + h1; //f(n) = g(n) + h(n)
    }

    public void calcH(Node a, Node b) { //node b is the destination, node a is current node
        this.h = Math.sqrt(Math.abs((a.getX()-b.getX())*(a.getX()-b.getX())+(a.getY()-b.getY())*(a.getY()-b.getY())));
    }

//    public void setG(double h) { //we dont know yet
//        this.h = h;
//    }

    public void setNode(Boolean node) {
        isNode = node;
    }
}
