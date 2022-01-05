import java.util.Objects;

//this class represents a node in the graph
public class Node implements Comparable<Node>{
    //each node has a parent
    //g(x) --> absolute distance between the starting node and the current node
    //f(x) --> full estimation of cost
    //h(x) --> from current to ending node

    //each node can also be a parents, so it has a list of edges
    public  Node parent;
    public double f; //f(x)
    public double g; //g(x)
    public double h; //h(x)
    private Boolean isNode; //after the input we understand if the node can be used or not (might be an obstacle)
    //this above is the parameter for flipNode
    private final int x; //for the coordinate: there are two different unit
    // x = 1 in PathFinder, Node, Grid -> equals to x = 30 in GUI since the length and width of each block is 30 but in pathfinder its assumes none
    private final int y;
    Boolean isVisited;

    public Node(int x1, int y1) {
        //values that are constant
        x = x1;
        y = y1;
        isVisited = false;

        //values that are going to get calculated
        h = 0; //0 means we haven't calculated the value since h, f,g are not constant
        g = 0;
        f = 0;
        parent = null; //as we calculate we connect the nodes by using this variable to find the path

        // values that change based on input
        isNode = true; //since from the beginning there are no obstacle its assumed every node is available
    }
    ///
    //instead of comparing memory addresses we have overwritten the comparing functions, so it compares the f(x) values
    @Override
    public int compareTo(Node other) {
        if (this.f > other.f) {
            return 1; //1 means gets added to the end
        } else {
            return this.f < other.f ? - 1: 0;  // -1 means add it to the front
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
    //calculators for f(x) and G(x) since, F(x) = h(x) + g(x)
    public double calcF() {
        this.f = g + h;
        return f;
    }
    public double calcG() {
        this.g = f - h;
        return g;
    }
    //calculating H by using pythagorean theorem to find the distance between end node and current node
    public double calcH(Node b) { //node b is the destination, x and y are the coordinates for the current node
        this.h = Math.sqrt(Math.abs((x-b.getX())*(x-b.getX())+(y-b.getY())*(y-b.getY())));
        return this.h;
    }

    //function that changes the node between being a block and active node: if its block you can't go through it for its path
    public void flipNode() {
        isNode = !isNode;
    }
    //shows what the node is
    public boolean isNode(){
        return isNode;
    }

    //similarly to compare we had to overwrite equals: instead of comparing memory addresses we have overwritten the comparing functions,
    // so it compares the f(x) values
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    //the hash code of each object is based on their (x,y) value used for equals
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    //this is used for testing the function in main without gui, it makes the nodes more readable
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
