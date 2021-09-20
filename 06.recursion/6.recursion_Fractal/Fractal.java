
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * A class designed to generate and draw fractals
 *
 * @author Trent Fulbright and Andrew Sweeris
 */
public class Fractal extends JFrame {

    private int level = 1;
    private Boolean HTreeActive = false;
    private Boolean SierpenskiCarpetActive = false;
    private Boolean SierpenskiTriangleActive = false;
    private Boolean OvalTreeActive = false;

    /**
     * Constructor method for Fractal class
     *
     */
    public Fractal() {
        setTitle("Fractals");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
        pack();
        //centers the frame

    }

    /**
     * Initializer for GUI properties including JButtons, JPanels, and the
     * Drawing Panel
     *
     */
    public void initGUI() {
        // set up your application
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(700, 700));
        setResizable(false);

        DrawingPanel canvas = new DrawingPanel();
        add(canvas);

        JPanel bottomPanel = new JPanel();
        add(bottomPanel, BorderLayout.SOUTH);
        JPanel topPanel = new JPanel();
        add(topPanel, BorderLayout.NORTH);

        // creation and addition of fractal buttons
        ButtonGroup fractals = new ButtonGroup();
        JRadioButton sierpenskiTriangle = new JRadioButton("Sierpenski Triangle");
        fractals.add(sierpenskiTriangle);
        JRadioButton sierpenskiCarpet = new JRadioButton("Sierpenski Carpet");
        fractals.add(sierpenskiCarpet);
        JRadioButton HTree = new JRadioButton("H Tree");
        fractals.add(HTree);
        JRadioButton ovaltree = new JRadioButton("Oval Tree Fractal");
        fractals.add(ovaltree);

        bottomPanel.add(sierpenskiTriangle);
        bottomPanel.add(sierpenskiCarpet);
        bottomPanel.add(HTree);
        bottomPanel.add(ovaltree);

        // Action listeners for buttons that impact drawing of fractals
        JButton clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                level = 1;
                repaint();
            }
        });
        bottomPanel.add(clear);

        JButton increase = new JButton("Increase");
        topPanel.add(increase);
        increase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                level++;
                if (level > 8) {
                    level = 8;
                }
                repaint();
            }
        });

        JButton decrease = new JButton("Decrease");
        topPanel.add(decrease);
        decrease.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (level > 1) {
                    level--;
                    repaint();
                }
            }
        });

        // Action listeners for fractal buttons
        HTree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                level = 1;
                HTreeActive = true;
                SierpenskiCarpetActive = false;
                SierpenskiTriangleActive = false;
                OvalTreeActive = false;
                repaint();

            }
        });
        sierpenskiCarpet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                level = 1;
                HTreeActive = false;
                SierpenskiCarpetActive = true;
                SierpenskiTriangleActive = false;
                OvalTreeActive = false;
                repaint();

            }
        });
        sierpenskiTriangle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                level = 1;
                HTreeActive = false;
                SierpenskiCarpetActive = false;
                SierpenskiTriangleActive = true;
                OvalTreeActive = false;
                repaint();

            }
        });
        ovaltree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                level = 1;
                HTreeActive = false;
                SierpenskiCarpetActive = false;
                SierpenskiTriangleActive = false;
                OvalTreeActive = true;
                repaint();

            }
        });

    }

    /**
     * Class made to host fractal drawings upon JPanel
     */
    class DrawingPanel extends JPanel {

        @Override
        /**
         * Method that calls recursive functions to draw fractals and is called
         * by repaint()
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g.setColor(new Color((int) (Math.random() * 200), (int) (Math.random() * 200), (int) (Math.random() * 200)));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // add code here and call helper methods
            if (HTreeActive) {
                drawHTree(level, 350, 350, 300, g);
            }
            if (SierpenskiCarpetActive) {
                drawSierpenskiCarpet(level, 150, 150, 400, g);
            }
            if (SierpenskiTriangleActive) {
                drawSierpenskiTriangle(level, 350, 350, 450, g);;
            }
            if (OvalTreeActive) {
                drawOvalTree(level, 350, 150, 500, g);
            }
        }

        /**
         * Recursive function to draw Sierpenski Carpet fractals
         *
         * @param level How many remaining levels of recursion left
         * @param x x-coordinate of square
         * @param y y-coordinate of square
         * @param side how long the square side is
         * @param g Graphics import
         */
        public void drawSierpenskiCarpet(int level, int x, int y, int side, Graphics g) {
            int squareSections = side / 3;

            g.fillRect(x + squareSections, y + squareSections, squareSections - 1, squareSections - 1);
            if (level > 0) {
                level = level - 1;
                drawSierpenskiCarpet(level, x, y, squareSections, g);
                drawSierpenskiCarpet(level, x + squareSections, y, squareSections, g);
                drawSierpenskiCarpet(level, x + 2 * squareSections, y, squareSections, g);
                drawSierpenskiCarpet(level, x, y + squareSections, squareSections, g);
                drawSierpenskiCarpet(level, x + 2 * squareSections, y + squareSections, squareSections, g);
                drawSierpenskiCarpet(level, x, y + 2 * squareSections, squareSections, g);
                drawSierpenskiCarpet(level, x + squareSections, y + 2 * squareSections, squareSections, g);
                drawSierpenskiCarpet(level, x + 2 * squareSections, y + 2 * squareSections, squareSections, g);

            }

        }

        /**
         * Recursive function to draw Sierpenski Triangle fractals
         *
         * @param level How many remaining levels of recursion left
         * @param x x-coordinate of triangle
         * @param y y-coordinate of triangle
         * @param length how long the triangle side is
         * @param g Graphics import
         */
        public void drawSierpenskiTriangle(int level, int x, int y, int length, Graphics g) {
            if (level == 0) {
                return;
            }

            g.drawLine(x - (length / 2), y + (length / 2), x + (length / 2), y + (length / 2));
            g.drawLine(x + (length / 2), y + (length / 2), x, y - (length / 2));
            g.drawLine(x, y - (length / 2), x - (length / 2), y + (length / 2));

            drawSierpenskiTriangle(level - 1, x - (length / 4), y + (length / 4), length / 2, g);
            drawSierpenskiTriangle(level - 1, x + (length / 4), y + (length / 4), length / 2, g);
            drawSierpenskiTriangle(level - 1, x, y - (length / 4), length / 2, g);
        }

        /**
         * Recursive function to draw H-Tree fractals
         *
         * @param level How many remaining levels of recursion left
         * @param x x-coordinate of H-Tree
         * @param y y-coordinate of H-Tree
         * @param length Length of H-Tree
         * @param g Graphics Import
         */
        public void drawHTree(int level, int x, int y, int length, Graphics g) {
            if (level == 0) {
                return;
            }

            g.drawLine(x - (length / 2), y, x + (length / 2), y);
            g.drawLine(x - (length / 2), y + (length / 2), x - (length / 2), y - (length / 2));
            g.drawLine(x + (length / 2), y + (length / 2), x + (length / 2), y - (length / 2));

            drawHTree(level - 1, x - (length / 2), y + (length / 2), length / 2, g);
            drawHTree(level - 1, x - (length / 2), y - (length / 2), length / 2, g);
            drawHTree(level - 1, x + (length / 2), y + (length / 2), length / 2, g);
            drawHTree(level - 1, x + (length / 2), y - (length / 2), length / 2, g);
        }

        /**
         * Recursive function to draw Oval Tree
         *
         * @param level How many remaining levels of recursion left
         * @param x x-coordinate of Oval Tree
         * @param y y-coordinate of Oval Tree
         * @param length diameter of Oval Tree
         * @param g Graphics Import
         */
        public void drawOvalTree(int level, int x, int y, int length, Graphics g) {
            if (level == 0) {
                return;
            }

            g.drawOval(x - length / 2, y, length / 2, length / 2);
            g.drawOval(x, y, length / 2, length / 2);
            g.drawOval(x - length / 4, y - length / 4, length / 2, length / 2);
            g.drawOval(x - length / 4, y + length / 4, length / 2, length / 2);

            drawOvalTree(level - 1, x - length / 4, y + length / 8, length / 2, g);
            drawOvalTree(level - 1, x + length / 4, y + length / 8, length / 2, g);
            drawOvalTree(level - 1, x, y - length / 8, length / 2, g);
            drawOvalTree(level - 1, x, y + 3 * (length / 8), length / 2, g);
        }

    }

    /**
     * Main method of Fractals.java
     *
     * @param args Imported String arguments
     */
    public static void main(String[] args) {
        new Fractal();
    }
}
