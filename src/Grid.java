//the class that represents the grid for the back-end
public class Grid {
    //this grid is used in pathFinder not GUI
    private final Node[][] grid; // an array representing the grid
    private final int rows; //number of columns and rows we have in our grid for pathfinder file
    private final int cols;
    public Grid(int rows, int cols) {
        //it constructs the grid according the input of rows and columns
        this.rows = rows;
        this.cols = cols;
        grid = new Node[rows][cols];
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Node temp = new Node(i, j);
                grid[i][j] = temp;
            }
        }
    }
    //basic getters
    public int getRows(){
        return this.rows;
    }
    public int getCols(){
        return this.cols;
    }
    public Node getNode(int rows, int column){
        return grid[rows][column];
    }
    //these functions are also used in the GUI:
    //if the node is a block according to its rows and columns
    // we "flip" the node making it a block or if its already block we bring it back
    public void flipNode(int rows, int column){
        grid[rows][column].flipNode();
    }
    //we check if the node is a block
    public boolean isValidNode(int rows, int column){
        return grid[rows][column].isNode();
    }
}
