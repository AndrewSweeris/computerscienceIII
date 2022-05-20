import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;


public class MazeGenerator extends JFrame {
    Cell[][] cell = new Cell[20][20];    // add instance variables
    int row = 0, col = 0, endRow = cell.length - 1, endCol = cell[endRow].length - 1;
    int mazeType = 0;
    private JPanel grid, data;
    public MazeGenerator() {
        initGUI();
        genMaze();
        setSize(cell.length * 60, cell[0].length * 20);
        setTitle("MazeGenerator");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add the menu bar

        // add a timer for solving the maze

        pack();
        //centers the frame
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (.5 * (screensize.width - getWidth())), (int) (.5 * (screensize.height - getHeight())), getWidth(), getHeight());
    }

    public static void main(String[] args) {
        new MazeGenerator();
    }
    private void genMaze() {
        switch (mazeType) {
            case 0: // backtracking normal
                backtracking();
                break;
            case 1: // backtracking anti-maze
            case 2: // prims
                prims();
                break;
            case 3: // anti-maze
            case 4: //
            case 5: // anti-maze
            case 6: //
            case 7: // anti-maze
        }
    }

    private JButton reroll, options, solve;
    private void initGUI() {
        setLayout(new GridLayout(1, 2));
        grid = new JPanel();
        data = new JPanel(new GridLayout(2, 1));
        data.setBackground(Color.BLACK);

        JPanel buttons = new JPanel();

        JLabel jlabel = new JLabel("Maze");
        jlabel.setFont(new Font("Verdana",60,60));
        jlabel.setBackground(Color.BLACK);
        jlabel.setForeground(Color.WHITE);
        data.add(jlabel);

        reroll = new JButton("New Maze");
        reroll.setFont(new Font("Verdana",10,10));
        reroll.setFocusable(false);
        buttons.add(reroll);

        options = new JButton("Options");
        options.setFont(new Font("Verdana",10,10));
        options.setFocusable(false);
        buttons.add(options);

        solve = new JButton("Solve");
        solve.setFont(new Font("Verdana",10,10));
        solve.setFocusable(false);
        buttons.add(solve);

        data.add(buttons);

        grid.setBackground(Color.BLACK);
        grid.setLayout(new GridLayout(endRow+1, endCol+1));
        grid.setSize(20 * (endRow+1), 20*(endCol+1));
        grid.setFocusable(true);
        grid.requestFocusInWindow();

        // create maze
        cell = new Cell[endRow+1][endCol+1];
        for (int y = 0; y < endCol+1; y++) {
            for (int x = 0; x < endRow+1; x++) {
                cell[x][y] = new Cell(x, y);
                grid.add(cell[x][y]);
            }
        }
        cell[0][0].setCurrent(true);
        cell[cell.length - 1][cell[cell.length - 1].length - 1].setEnd(true);
        repaint();


        // add listeners
        grid.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                keyReleasedKeyEvent(e);
            }
        });
        reroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMaze();
            }
        });
        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openOptionsPane();
            }
        });
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solve();
            }
        });


        add(data, BorderLayout.WEST);
        add(grid, BorderLayout.CENTER);
    }

    private void openOptionsPane() {
        JDialog menu = new JDialog();
        menu.setSize(400, 1400);
        menu.setLayout(new GridLayout(3, 1));
        menu.setResizable(false);

        JPanel rows = new JPanel();
        rows.setLayout(new GridLayout(2,1));
        rows.add(new JLabel("Rows"));
        JTextField rowField = new JTextField(20);
        rowField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = rowField.getText();
                System.out.println(text);
                endRow = (int)(Integer.parseInt(text));
            }
        });
        rows.add(rowField);

        JPanel cols = new JPanel();
        cols.setLayout(new GridLayout(2, 1));
        cols.add(new JLabel("Columns"));
        JTextField colField = new JTextField(20);
        colField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = colField.getText();
                System.out.println(text);
                endCol = (int)(Integer.parseInt(text));
            }
        });
        cols.add(colField);

        JPanel radioButtonsType = new JPanel();
        ButtonGroup mazes = new ButtonGroup();
        JRadioButton one = new JRadioButton("Backtracking");
        one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mazeType = 0;
            }
        });
        JRadioButton two = new JRadioButton("Prims");
        two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mazeType = 2;
            }
        });
        /*
        JRadioButton three = new JRadioButton("Three");
        three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mazeType = 4;
            }
        });
        JRadioButton four = new JRadioButton("Four");
        four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mazeType = 6;
            }
        });
         */
        mazes.add(one);
        mazes.add(two);
        /*
        mazes.add(three);
        mazes.add(four);
         */

        switch (mazeType) {
            case 0:
            case 1: mazes.setSelected(one.getModel(), true); break;
            case 2:
            case 3: mazes.setSelected(two.getModel(), true); break;
            case 4:
                /*
            case 5: mazes.setSelected(three.getModel(), true); break;
            case 6:
            case 7: mazes.setSelected(four.getModel(), true); break;
                 */
        }

        radioButtonsType.setLayout(new GridLayout(5, 1));
        radioButtonsType.add(new JLabel("Maze Type"));
        radioButtonsType.add(one);
        radioButtonsType.add(two);
        /*
        radioButtonsType.add(three);
        radioButtonsType.add(four);
         */

        menu.add(rows);
        menu.add(cols);
        menu.add(radioButtonsType);
        menu.pack();

        menu.setVisible(true);
    }

    private void keyReleasedKeyEvent(KeyEvent e) {
        // check the four different key presses
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            // move up if this cell does not have a top wall
            if (cell[row][col].wall[Cell.TOP] == false) {
                moveTo(row, col-1, Cell.TOP, Cell.BOTTOM);
                // move up more if this is a long column(loop checking top-false,left-true,right-true)
                while (cell[row][col].wall[Cell.TOP] == false && cell[row][col].wall[Cell.LEFT] && cell[row][col].wall[Cell.RIGHT]) {
                    moveTo(row, col-1, Cell.TOP, Cell.BOTTOM);
                }
            }
        }
        // 3 more cases
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // move up if this cell does not have a top wall
            if (cell[row][col].wall[Cell.BOTTOM] == false) {
                moveTo(row, col+1, Cell.BOTTOM, Cell.TOP);
                // move up more if this is a long column(loop checking top-false,left-true,right-true)
                while (cell[row][col].wall[Cell.BOTTOM] == false && cell[row][col].wall[Cell.LEFT] && cell[row][col].wall[Cell.RIGHT]) {
                    moveTo(row, col+1, Cell.BOTTOM, Cell.TOP);
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // move up if this cell does not have a top wall
            if (cell[row][col].wall[Cell.LEFT] == false) {
                moveTo(row - 1, col, Cell.LEFT, Cell.RIGHT);
                // move up more if this is a long column(loop checking top-false,left-true,right-true)
                while (cell[row][col].wall[Cell.LEFT] == false && cell[row][col].wall[Cell.TOP] && cell[row][col].wall[Cell.BOTTOM]) {
                    moveTo(row - 1, col, Cell.LEFT, Cell.RIGHT);
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // move up if this cell does not have a top wall
            if (cell[row][col].wall[Cell.RIGHT] == false) {
                moveTo(row + 1, col, Cell.RIGHT, Cell.LEFT);
                // move up more if this is a long column(loop checking top-false,left-true,right-true)
                while (cell[row][col].wall[Cell.RIGHT] == false && cell[row][col].wall[Cell.TOP] && cell[row][col].wall[Cell.BOTTOM]) {
                    moveTo(row + 1, col, Cell.RIGHT, Cell.LEFT);
                }
            }
        }
        // check to see if the game is over
        if (row == endRow && col == endCol) {
            String message = "Congratulations! You solved it.";
            JOptionPane.showMessageDialog(this, message);
            createMaze();
        }
    }

    private void moveTo(int nextRow, int nextCol, int firstDirection, int secondDirection) {
        // update the current cell(change boolean curr to false) and add the path(firstD)
        cell[row][col].setCurrent(false);
        if (!cell[row][col].getPath(firstDirection)) {
            cell[row][col].openPath(firstDirection);
            cell[nextRow][nextCol].setCurrent(true);
            cell[nextRow][nextCol].openPath(secondDirection);
        }
        else {
            cell[row][col].closePath(firstDirection);
            cell[nextRow][nextCol].setCurrent(true);
            cell[nextRow][nextCol].closePath(secondDirection);
        }

        row = nextRow;
        col = nextCol;
        // and repaint since changes were made
        repaint();

    }

    private void createMaze() {
        remove(grid);
        grid = new JPanel();
        row = 0;
        col = 0;
        // add code to create the grid and call carvePassages() below
        grid.setBackground(Color.BLACK);
        grid.setLayout(new GridLayout(endRow+1, endCol+1));
        grid.setSize(400, 400);

        // create maze
        cell = new Cell[endRow+1][endCol+1];
        for (int y = 0; y < endCol+1; y++) {
            for (int x = 0; x < endRow+1; x++) {
                cell[x][y] = new Cell(x, y);
                cell[x][y].SIZEV = 400 / (endRow+1);
                cell[x][y].SIZEH = 400 / (endCol+1);
                grid.add(cell[x][y]);
            }
        }
        cell[0][0].setCurrent(true);
        cell[cell.length - 1][cell[cell.length - 1].length - 1].setEnd(true);
        add(grid);
        grid.setFocusable(true);
        reroll.setFocusable(false);
        options.setFocusable(false);
        solve.setFocusable(false);
        grid.requestFocusInWindow();
        repaint();


        // add listeners
        grid.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                keyReleasedKeyEvent(e);
            }
        });
        genMaze();
        grid.revalidate();
    }

    private boolean inBounds(int r, int c) {
        if (r < 0 || c < 0) return false;
        if (r >= cell.length || c >= cell[r].length) return false;
        return true;
    }

    private boolean isAvailable(int r, int c) {
        //must be inbounds and have all walls
        return cell[r][c].hasAllWalls();
    }

    private void backtracking() {
        row = 0;
        col = 0;
        Stack<Point> stack = new Stack<>();
        LinkedList<Point> list = new LinkedList<>();
        stack.add(new Point((int) (Math.random() * cell.length), (int) (Math.random() * cell.length)));

        while (!stack.isEmpty()) {
            list.clear();
            Point current = stack.pop();

            if (inBounds(current.x - 1, current.y) && isAvailable(current.x - 1, current.y))
                list.add(new Point(current.x - 1, current.y));
            if (inBounds(current.x + 1, current.y) && isAvailable(current.x + 1, current.y))
                list.add(new Point(current.x + 1, current.y));
            if (inBounds(current.x, current.y - 1) && isAvailable(current.x, current.y - 1))
                list.add(new Point(current.x, current.y - 1));
            if (inBounds(current.x, current.y + 1) && isAvailable(current.x, current.y + 1))
                list.add(new Point(current.x, current.y + 1));

            if (list.size() > 0) {
                int index = (int) (Math.random() * list.size());
                if (list.size() > 1) {
                    stack.add(current);
                }
                Point p = list.get(index);
                cell[current.x][current.y].openTo(cell[p.x][p.y]);
                stack.add(p);
            }
        }
    }
    private void prims() {
        row = 0;
        col = 0;
        LinkedList<Point> avail = new LinkedList<>();
        LinkedList<Point> list = new LinkedList<>();
        avail.add(new Point((int) (Math.random() * cell.length), (int) (Math.random() * cell.length)));

        while (!avail.isEmpty()) {
            list.clear();
            Point current = avail.remove((int)(Math.random()*avail.size()));

            if (inBounds(current.x - 1, current.y) && isAvailable(current.x - 1, current.y))
                list.add(new Point(current.x - 1, current.y));
            if (inBounds(current.x + 1, current.y) && isAvailable(current.x + 1, current.y))
                list.add(new Point(current.x + 1, current.y));
            if (inBounds(current.x, current.y - 1) && isAvailable(current.x, current.y - 1))
                list.add(new Point(current.x, current.y - 1));
            if (inBounds(current.x, current.y + 1) && isAvailable(current.x, current.y + 1))
                list.add(new Point(current.x, current.y + 1));

            if (list.size() > 0) {
                int index = (int) (Math.random() * list.size());
                if (list.size() > 1) {
                    avail.add(current);
                }
                Point p = list.get(index);
                cell[current.x][current.y].openTo(cell[p.x][p.y]);
                avail.add(p);
            }
        }
    }

    HashMap<Point, Point> parentMap;
    HashSet<Point> visited;
    private void solve() {
        //BFS or DFS alogorithm
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[i].length; j++) {
                cell[i][j].closePath(0);
                cell[i][j].closePath(1);
                cell[i][j].closePath(2);
                cell[i][j].closePath(3);
                cell[i][j].setCurrent(false);
                cell[i][j].setEnd(false);
            }
        }
        cell[0][0].setCurrent(true);
        cell[cell.length-1][cell[0].length-1].setEnd(true);
        parentMap = new HashMap<>();
        visited = new HashSet<>();
        solved = false;
        solver(0,0);
        Stack<Point> stack = new Stack<>();
        Point curr = new Point(cell.length-1,cell[0].length-1);
        while (curr!=null) {
            stack.add(curr);
            curr = parentMap.get(curr);
        }
        curr = stack.pop();
        while (!stack.isEmpty()) {
            cell[curr.x][curr.y].setCurrent(false);
            Point temp = stack.pop();
            int direction = getPath(cell[curr.x][curr.y],cell[temp.x][temp.y]);
            cell[curr.x][curr.y].openPath(direction);
            direction = (direction + 2)%4;
            cell[temp.x][temp.y].openPath(direction);
            curr=temp;
            cell[curr.x][curr.y].setCurrent(true);
        }
        repaint();
    }

    private int getPath(Cell one, Cell two) {
        if (one.X>two.X) return Cell.LEFT;
        if (one.X<two.X) return Cell.RIGHT;
        if (one.Y>two.Y) return Cell.TOP;
        return Cell.BOTTOM;
    }

    boolean solved = false;
    private void solver(int x, int y) {
        visited.add(new Point(x, y));
        if (x==cell.length-1&&y==cell[0].length-1) {
            solved=true;
        }
        if (!solved) {
            LinkedList<Point> list = new LinkedList<>();
            if (inBounds(x - 1, y) && !visited.contains(new Point(x - 1, y))&&!cell[x][y].wall[Cell.LEFT])
                list.add(new Point(x - 1, y));
            if (inBounds(x + 1, y) && !visited.contains(new Point(x + 1, y))&&!cell[x][y].wall[Cell.RIGHT])
                list.add(new Point(x + 1, y));
            if (inBounds(x, y - 1) && !visited.contains(new Point(x, y-1))&&!cell[x][y].wall[Cell.TOP])
                list.add(new Point(x, y - 1));
            if (inBounds(x, y + 1) && !visited.contains(new Point(x, y+1))&&!cell[x][y].wall[Cell.BOTTOM])
                list.add(new Point(x, y + 1));
            Iterator<Point> it = list.iterator();
            while (it.hasNext()) {
                Point temp = it.next();
                parentMap.put(temp, new Point(x, y));
                solver(temp.x, temp.y);
            }
        }
    }
}

class Cell extends JPanel {

    public static final int TOP = 0;
    public static final int RIGHT = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;
    public static int SIZEV = 20;
    public static int SIZEH = 20;
    public final int X;
    public final int Y;
    boolean[] wall = {true, true, true, true};
    private boolean[] path = {false, false, false, false};
    private boolean current, end;

    //constructor
    public Cell(int X, int Y) {
        this.X = X;
        this.Y = Y;
        current = false;
        end = false;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, SIZEH, SIZEV);
        g.setColor(Color.BLACK);
        // draws top wall
        if (wall[TOP])
            g.drawLine(0, 0, SIZEV, 0);
        // draws left wall
        if (wall[LEFT])
            g.drawLine(0, 0, 0, SIZEH);
        g.setColor(Color.GREEN);
        if (path[TOP]) {
            g.drawLine(SIZEH / 2, SIZEV / 2, SIZEH / 2, 0);
        }
        if (path[RIGHT]) {
            g.drawLine(SIZEH / 2, SIZEV / 2, SIZEH, SIZEV / 2);
        }
        if (path[BOTTOM]) {
            g.drawLine(SIZEH / 2, SIZEV / 2, SIZEH / 2, SIZEV);
        }
        if (path[LEFT]) {
            g.drawLine(SIZEH / 2, SIZEV / 2, 0, SIZEV / 2);
        }
        if (current) {
            g.setColor(Color.GREEN);
            g.fillOval(4, 4, SIZEH - 8, SIZEV - 8);
        }
        if (end) {
            g.setColor(Color.RED);
            g.fillOval(4, 4, SIZEH - 8, SIZEV - 8);
        }
        repaint();
    }

    public Dimension getPreferredSize() {
        Dimension size = new Dimension(SIZEH, SIZEV);
        return size;
    }

    public boolean hasAllWalls() {
        return wall[0] && wall[1] && wall[2] && wall[3];
    }

    public void removeWall(int w) {
        wall[w] = false;
        repaint();
    }

    public void openPath(int DIRECTION) {
        path[DIRECTION] = true;
    }

    public void closePath(int DIRECTION) {
        path[DIRECTION] = false;
    }
    public boolean getPath(int DIRECTION) {
        return path[DIRECTION];
    }

    public void openTo(Cell neighbor) {
        // this method opens the wall up
        if (neighbor.Y < Y) {
            neighbor.removeWall(BOTTOM);
            removeWall(TOP);
        } else if (neighbor.Y > Y) {
            neighbor.removeWall(TOP);
            removeWall(BOTTOM);
        } else if (neighbor.X < X) {
            neighbor.removeWall(RIGHT);
            removeWall(LEFT);
        } else {
            neighbor.removeWall(LEFT);
            removeWall(RIGHT);
        }
        repaint();
    }
}