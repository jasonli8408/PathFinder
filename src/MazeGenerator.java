import java.util.HashSet;
import java.util.List;
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
            for (int i = y ; i < width ; i++) {
                if (i != door && !grid.getNode(wx,i).isDoor()) {
                    grid.getNode(wx, i).makeBlock();
                    blocks.add(grid.getNode(wx,i));
                } else if (i == door) {
                    grid.getNode(wx, i).makeDoor();
                }
            }
            makeMaze(x , y,width,wx - x);
            makeMaze(wx + 1 , y, width, height - wx - 1);
        } else {
            int wy = y + rand.nextInt(width - 1);
            int door = x + rand.nextInt(height - 1);

            for (int i = x ; i < height ; i++) {
                if (i != door && !grid.getNode(i, wy).isDoor()) {
                    grid.makeBlock(i, wy);
                    blocks.add(grid.getNode(i, wy));
                } else if (i == door) {
                    grid.getNode(i, wy).makeDoor();

                }
            }

            makeMaze(x,y, wy - y, height);
            makeMaze(x,wy + 1, width - wy - 1 , height);

        }

        return blocks;
    }
    public Grid getGrid() {
        return grid;
    }
}