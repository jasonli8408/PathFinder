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

        //now we choose where the wall will be drawn from
        int wx = x + (horizontal ? rand.nextInt(height - 1) : 0); // if it is horizontal, we choose from a row
        int wy = y + (horizontal ? 0 : rand.nextInt(width - 1)); //if it is horizontal, should not be drawn from any y coord, so it is zer

        //determnining the passage in the wall in x y coordinate
        int pwx = wx + (horizontal ? 0 : rand.nextInt(height - 1)); //we choose a pathway from a vertical point, the height changes, so that is why we did a random on height
        int pwy = wy + (horizontal ? rand.nextInt(width - 1) : 0);
        //if horizontal, we choose path from a Y value, since it is on column side

        //determing the length
        int len = horizontal ? width : height;
        //make the wall and passage here
        //two cases
        if (horizontal) {
            for (int i = y ; i < len ; i++) {
                grid.makeBlock(wx, i);
                    //check if it is the passage coord
                grid.makePath(pwx, pwy);

                if (!(grid.getNode(wx,i).isNode())) {
                    blocks.add(grid.getNode(wx,i));
                }



            }
        } else {
            //for loop
            for (int i = x ; i < len ; i++) {
                    grid.makeBlock(i, wy);
                    //check if it is the passage coord
                   grid.makePath(pwx, pwy);

                if (!(grid.getNode(i, wy).isNode())) {
                    blocks.add(grid.getNode(i,wy));
                }
            }
        }

        //update the width and height, and get the new sub recrusive area, can have two possiblities.
        if (horizontal) {
            makeMaze(x,y,width, wx - x + 1);
            makeMaze(wx , y, width, height - (wx - x) - 1);
        } else {
            makeMaze(x,y,wy - y + 1, height);
            makeMaze(x, wy , width - (wy - y) - 1 , height);
        }
//        int nx, ny ;
//        nx = wx;
//        ny = wy;
//
//        //first case
//        int tempwidth = horizontal ? width : wy - y + 1;
//        int tempheight = horizontal ? wx - x + 1 : height;
//        makeMaze(nx,ny, tempwidth, tempheight);
//
//
//
//        //second case
//        int tempwidth2 = horizontal ? width :  width - wy  ;
//        int tempHeight2 = horizontal ? height - wx  : height;
//        makeMaze(nx,ny,tempwidth2, tempHeight2);


        return blocks;


    }

    public Grid getGrid() {
        return grid;
    }
}
