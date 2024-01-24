package Visual;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MyCanvas extends Canvas {
     double width, height;
     BufferedImage image;
     public MyCanvas(double width, double height){
          this.width = width;
          this.height = height;
          image = new BufferedImage((int)width*2, (int)height*2, BufferedImage.TYPE_INT_RGB);
     }

     public void paint(Graphics g){
          g.drawImage(image,0,0,Color.white,null);
     }

     public void update(Graphics g){
          paint(g);
     }

}
