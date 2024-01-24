package Visual;

import MathStuff.Formulas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Screen{

    private JFrame frame;
    private double height;//-H to H in v direction
    private  double width;//-W to W in u direction
    private static double N = 600;//600
    private double Rho = Math.PI/3;//60 degrees
    private static double aspect = 4.0/3.0;
    private int rows;
    private int cols;
    private MyCanvas canvas;

    public Screen() {
        double[] heightAndWidth = Formulas.getHeightAndWidth(N, Rho, aspect);
        height = heightAndWidth[0];
        width = heightAndWidth[1];


        this.cols = (int)width*2;
        this.rows = (int)height*2;
        this.frame = new JFrame();
        this.canvas = new MyCanvas((int)width, (int)height);
        frame.add(canvas);
        frame.setTitle("Ray Tracing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(this.cols, this.rows);
        frame.setVisible(true);
    }

    public void paint(double x, double y, Color color){
        canvas.image.setRGB((int)x,(int)y,color.getRGB());
        canvas.repaint();
    }
    public int getWidth(){
        return (int) width;
    }
    public int getHeight(){
        return (int) height;
    }

    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public double getN(){
        return N;
    }


    public JFrame getFrame(){
        return frame;
    }

    
}
