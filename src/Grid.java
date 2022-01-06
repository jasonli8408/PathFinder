public class Grid {
    private final Node[][] grid;
    private final int rows;
    private final int cols;
    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Node[rows][cols];
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Node temp = new Node(j, i);
                grid[j][i] = temp;
            }
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

    public void makeBlock(int rows, int column){
        grid[rows][column].makeBlock();
    }
    public void flipNode(int rows, int column){
        grid[rows][column].flipNode();
    }

    public void makePath(int rows, int cols) {
        grid[rows][cols].makePath();
    }


    public boolean isNotDoor(int rows, int col) {
        return !grid[rows][col].isDoor();
    }

    public boolean isValidNode(int rows, int column){

            return grid[rows][column].isNode();
        }





}
