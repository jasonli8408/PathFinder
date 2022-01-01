import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;



public class GUI extends JPanel implements MouseWheelListener, MouseListener, KeyListener, ActionListener {

    private JFrame window;
    private PathFinder pathFinder;
    private final static int gridDimention = 30;
    private Character keyRightNow;
    private Node startNode;
    private Node endNode;
    int isStartOn = 0;
    int isEndOn = 0;
    private Node startNodeForFunc;
    private Node EndNodeForFunc;



 //
    public GUI() {
        addMouseListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        keyRightNow = (char) '0';
        startNode = null;
        endNode = null;
        window = new JFrame();
        window.setContentPane(this);
        window.getContentPane().setPreferredSize(new Dimension(900,900));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //how it would close
        window.setTitle("A* algorithm");
        window.pack();
        window.setVisible(true);

        revalidate();
        repaint();

    }


    {
        JFrame frame = new JFrame();
        frame.setTitle("Control Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Font size and style
        Font font = new Font("Verdana", Font.BOLD, 30);

        JButton chooseStart, chooseEnd, chooseObstacles, findPath, incrementGridSize, decrementGridSize;

        class RoundedBorder implements Border {
            private final int radius;

            RoundedBorder(int radius) {
                this.radius = radius;
            }

            public Insets getBorderInsets(Component c) {
                return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
            }

            public boolean isBorderOpaque() {
                return true;
            }

            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            }
        }

        chooseStart = new JButton("Choose Start Node");
        chooseStart.setSize(new Dimension(10, 40));
        chooseStart.setBorder(new RoundedBorder(10));
        chooseStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        chooseEnd = new JButton("Choose End Node");
        chooseEnd.setSize(new Dimension(10, 40));
        chooseEnd.setBorder(new RoundedBorder(10));
        chooseEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        chooseObstacles = new JButton("Create Obstacles");
        chooseObstacles.setSize(new Dimension(10, 40));
        chooseObstacles.setBorder(new RoundedBorder(10));
        chooseObstacles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        findPath = new JButton("A* Find Path");
        findPath.setSize(new Dimension(10, 40));
        findPath.setBorder(new RoundedBorder(10));
        findPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        incrementGridSize = new JButton("Increment Grid Size");
        incrementGridSize.setSize(new Dimension(10, 40));
        incrementGridSize.setBorder(new RoundedBorder(10));
        incrementGridSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        decrementGridSize = new JButton("Decrement Grid Size");
        decrementGridSize.setSize(new Dimension(10, 40));
        decrementGridSize.setBorder(new RoundedBorder(10));
        decrementGridSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(6,1));
        p2.add(chooseStart);
        p2.add(chooseEnd);
        p2.add(chooseObstacles);
        p2.add(findPath);
        p2.add(incrementGridSize);
        p2.add(decrementGridSize);


        frame.setLayout(new BorderLayout());
        frame.add(p2,BorderLayout.LINE_END);
        frame.pack();
        frame.setVisible(true);
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



        if (endNode != null) {
            g.setColor(Color.red);
            g.fillRect(endNode.getX() + 1, endNode.getY() + 1, gridDimention - 1, gridDimention - 1);
        }

        if (startNode != null) {
            g.setColor(Color.blue);
            g.fillRect(startNode.getX() + 1, startNode.getY() + 1, gridDimention - 1, gridDimention - 1);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (keyRightNow == 'e' || keyRightNow == 'E') {
                isEndOn++;
                int xSub = e.getX() % gridDimention;
                int ySub = e.getY() % gridDimention;
                if (isEndOn % 2 == 1 && endNode == null) {
                    endNode = new Node(e.getX() - xSub, e.getY() - ySub);
                    EndNodeForFunc = new Node(endNode.getX()/30, endNode.getY()/30);
                }
                else{
                    endNode = null;
                    EndNodeForFunc = null;
                }
                repaint();

            }
            else if (keyRightNow == 's' || keyRightNow == 'S') {
                isStartOn++;
                int xSub = e.getX() % gridDimention;
                int ySub = e.getY() % gridDimention;
                if (isStartOn % 2 == 1 && endNode == null) {
                    startNode = new Node(e.getX() - xSub, e.getY() - ySub);
                    startNodeForFunc = new Node(startNode.getX()/30, startNode.getY()/30);
                }
                else{
                    startNode = null;
                    startNodeForFunc = null;
                }
                repaint();
            }







        }
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

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        keyRightNow = key;
    }
}
