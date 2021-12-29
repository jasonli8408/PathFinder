public class Grid {
    private Node[][] grid;
    public Grid(int rows, int cols) {
        grid = new Node[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                Node temp = new Node(i,j);
                grid[i][j] = temp;
            }
            //
        }
    }
}
