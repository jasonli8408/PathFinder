import javax.swing.*;
import java.awt.*;

public class GUI {


    public GUI(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(40 ,40));
        frame.add(panel, BorderLayout.CENTER); // adds the pannel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //how it would close
        frame.setTitle("A* algorithm");
        frame.pack();
        frame.setVisible(true);
    }

}
