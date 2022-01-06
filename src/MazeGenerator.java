
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MazeGenerator {
    private Grid grid;
    private boolean horizontal;
    private Set<Node> blocks;

    public MazeGenerator(Grid grid) {
        this.grid = grid;
        horizontal = false;
        blocks = new HashSet<>();
    }

    public boolean chooseOrientation(int width, int height) {

        if (width < height) {
            horizontal = true;
        } else if (height < width) {
            horizontal = false;
        } else {
            Random rand = new Random();
            horizontal = rand.nextBoolean();
        }

        return horizontal;
    }


    public boolean isStartRow(int x) {
        for (int i = 0 ; i < grid.getCols() ; i++) {
            if (!(grid.isValidNode(x,i)) && grid.isNotDoor(x,i)) {
                return false;
            }
        }


        return true;
    }


    public boolean isStartCol(int y) {
        for (int i = 0 ; i < grid.getRows() ; i++) {
            if (!(grid.isValidNode(i,y)) && grid.isNotDoor(i,y)) {
                return false;
            }
        }


        return true;
    }


    public Set<Node> makeMaze(int x, int y, int width, int height) {
        Random rand = new Random();
        //base case
        if (width <= 1 || height <= 1) {
            return blocks;
        }

        //here we choose the the direction the wall will be drawn. If horizontal is true, we draw it horizontally and vice versa
        horizontal = chooseOrientation(width, height);

        if (horizontal) {
            //horizontal
            int wx = x + rand.nextInt(height - 1);
            int door = y + rand.nextInt(width - 1);
            int door2 = y + rand.nextInt(width - 1);
            if (isStartRow(wx) && y == 0) {
                for (int i = y ; i < grid.getCols() ; i++) {
                    if (i != door && i != door2 && grid.isNotDoor(wx, i)) {
                        grid.getNode(wx, i).makeBlock();
                        blocks.add(grid.getNode(wx,i));
                    } else if (i == door || i == door2){
                        grid.getNode(wx, i).makeDoor();
                    }
                }
                ///
            } else {
                for (int i = y; i < y + width - 1; i++) {
                    if (i != door && i != door2 && grid.isNotDoor(wx, i)) {
                        grid.getNode(wx, i).makeBlock();
                        blocks.add(grid.getNode(wx, i));
                    } else if (i == door || i == door2) {
                        grid.getNode(wx, i).makeDoor();
                    }
                }
            }
            makeMaze(x , y,width,wx - x + 1);
            makeMaze(wx + 1 , y, width, height - wx);
        } else {
            int wy = y + rand.nextInt(width - 1);
            int door = x + rand.nextInt(height - 1);
            int door2 = x + rand.nextInt(height - 1);

            if (isStartCol(wy) && x ==0) { // checks if this row has not been modified
                for (int i = x ; i < grid.getRows() ; i++) {
                    if (i != door && i != door2 && grid.isNotDoor(i,wy)) {
                        grid.makeBlock(i, wy);
                        blocks.add(grid.getNode(i, wy));
                    } else {
                        if (i == door) {
                            grid.getNode(i, wy).makeDoor();
                        } else if (i == door2) {
                            grid.getNode(i, wy).makeDoor();
                        }
                    }
                }
            } else {

                for (int i = x; i < x + height - 1; i++) {
                    if (i != door && i != door2 && grid.isNotDoor(i, wy)) {
                        grid.makeBlock(i, wy);
                        blocks.add(grid.getNode(i, wy));
                    } else {
                        if (i == door || i == door2) {
                            grid.getNode(i, wy).makeDoor();
                        }
                    }
                }
            }


            makeMaze(x,y, wy - y + 1, height);
            makeMaze(x,wy + 1, width - wy , height);

        }

        return blocks;
    }
    public Grid getGrid() {
        return grid;
    }
}