package Textures;

import MathStuff.Vector3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



import javax.imageio.ImageIO;

public class PictureTexture {

    public static double[] getColor(Vector3 point, String imageName){
        double[] color = new double[3];
        double[][] pixmap = pngToPixmap(imageName);
        int width = pixmap.length;
        int height = pixmap[0].length;
        int x = (int) (point.getX() * width);
        int y = (int) (point.getY() * height);
        if (x < 0) x = 0;
        if (x >= width) x = width - 1;
        if (y < 0) y = 0;
        if (y >= height) y = height - 1;
        color[0] = pixmap[x][y];
        color[1] = pixmap[x][y];
        color[2] = pixmap[x][y];
        return color;
    }

    public static double[][] pngToPixmap(String imageName) {
        try {
            String imagePath = "src/"+imageName;
            BufferedImage image = ImageIO.read(new File(imagePath));

            int width = image.getWidth();
            int height = image.getHeight();
            double[][] pixmap = new double[width][height];

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);

                    // Extract RGB values
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;

                    // Convert RGB values to grayscale and normalize between 0 and 1
                    double grayscaleValue = (red + green + blue) / 3.0 / 255.0;
                    pixmap[x][y] = grayscaleValue;
                }
            }

            return pixmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
