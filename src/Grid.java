public class Grid {
    private Node[][] grid;
    private final int rows;
    private final int cols;
    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Node[rows][cols];
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Node temp = new Node(i, j);
                grid[i][j] = temp;
            }
            //
        }
    }

    public int getRows(){
        return this.rows;
    }
    public int getCols(){
        return this.cols;
    }

    public Node getNode(int rows, int column){
        return grid[rows][column];
    }

    public boolean isValidNode(int rows, int column){
        return grid[rows][column].isNode();
    }

}
