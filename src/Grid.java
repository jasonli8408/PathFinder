public class Grid {
    private node[][] grid;
    public Grid(int x, int y) {
        this.grid = new node[y][x];
        for(int i = 0; i < y; i++){
            for(int j =0; j < x; j++){
                grid[i][j] = new node;

            }
        }
    }

}
