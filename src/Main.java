import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        MazeGenerator mazeGenerator = new MazeGenerator(new Grid(90,90));
        mazeGenerator.makeMaze(0,0, 90,90);
        new GUI();


    }
}
