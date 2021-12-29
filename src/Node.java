import java.util.Objects;

//this class represents a node in the graph
public class Node implements Comparable<Node>{
    //each node has a parent

    //g(x) --> absolute distance moving from one node to another
    //f(x) --> full estimation of cost
    //h(x) --> from n to target end point

    //each node can also be a parents, so it has a list of edges
    public  Node parent;
    public double f;
    public double g;
    public double h;
    private Boolean isNode; //after the input we understand if the node can be used or not (might be an obstacle)
    private final int x; //for the coordinate
    private final int y;


    public Node(int x1, int y1) {

        //values that are constant
        x = x1;
        y = y1;
        //values that are going to get calculated
        h = 0; //-1 means we haven't calculated the value since h, f,g are not constant
        g = 0;
        f = 0;
        parent = null;
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


    public double calcF() {
        this.f = g + h;
       return f;
    }

    public double calcG() {
        this.g = f - h;
        return g;
    }

    public double calcH(Node b) { //node b is the destination, node a is current node
        this.h = Math.sqrt(Math.abs((x-b.getX())*(x-b.getX())+(y-b.getY())*(y-b.getY())));
        return this.h;
    }

    public void setNode(Boolean node) {
        isNode = node;
    }

    public boolean isNode(){
        return isNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
