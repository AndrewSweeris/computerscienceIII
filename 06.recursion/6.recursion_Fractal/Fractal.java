// created by Andrew Sweeris and Trent Fulbright

import java.awt.*;
import javax.swing.*;
public class Fractal extends JFrame {

    BorderLayout lm = new BorderLayout();

    public Fractal() {
     setTitle("Fractals");
     setVisible(true);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     initGUI();
     pack();
     
     //centers the frame
     Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
     setBounds(0, 0, screensize.width, (int)screensize.getHeight());
    }
    public void initGUI(){
     // set up your application
    	JButton clear = new JButton("Clear");
    	clear.setPreferredSize(new Dimension(100, 60));
    	lm.addLayoutComponent(clear, lm.SOUTH);

    	JButton increase = new JButton("Increase");
    	clear.setPreferredSize(new Dimension(100, 60));
    	lm.addLayoutComponent(increase, lm.SOUTH);

    	JButton decrease = new JButton("Decrease");
    	clear.setPreferredSize(new Dimension(100, 60));
    	lm.addLayoutComponent(decrease, lm.SOUTH);

        setLayout(lm);
    }
   
    class DrawingPanel extends JPanel{
     int level;
     // add a constructor
     
     public void paintComponent(Graphics g){
       super.paintComponent(g);
       Graphics2D g2 = (Graphics2D)g;
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       
       // add code here and call helper methods
     }
     
     // example of a helper method
     public void drawKockSnowflake(int level, int x, int A, Graphics g) {
                
     }
     
     public void drawFourDragonFractal(int deep, int x, int a, Graphics g) {
    	 
     }
     
     public void drawTesselatedDragonFractal(int deep, int x, int a, Graphics g) {
    	 
     }
     
     public void drawPythagorasTreeFractal(int deep, int x, int a, Graphics g) {
    	 
     }
   }
    
   public static void main(String[] args) {
        new Fractal();
   }
}
