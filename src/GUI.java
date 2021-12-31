import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel implements MouseWheelListener, MouseListener, KeyListener, ActionListener {

    private JFrame window;
    private PathFinder pathFinder;
    private final static int gridDimention = 25;



    public GUI(){
        window = new JFrame();
        window.setContentPane(this);
        window.getContentPane().setPreferredSize(new Dimension(1000,1000));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //how it would close
        window.setTitle("A* algorithm");
        window.pack();
        window.setVisible(true);
        window.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);



        g.setColor(Color.lightGray);
        for (int y = 0; y < this.getHeight(); y += gridDimention) {
            for (int x = 0; x < this.getWidth(); x += gridDimention) {
                g.drawRect(x, y , gridDimention, gridDimention);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}
