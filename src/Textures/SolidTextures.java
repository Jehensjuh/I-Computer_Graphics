package Textures;

import MathStuff.Vector3;

import java.util.Random;

public class SolidTextures {

    protected static Noise noise = new Noise();

    public static double[] checkerBoard(Vector3 point){
        double[] color = new double[3];
        //odd or even?
        //if odd, white
        if((Math.floor(point.getX()) + Math.floor(point.getY()) + Math.floor(point.getZ())) % 2 != 0){
            color[0] = 1;
            color[1] = 1;
            color[2] = 1;
        }else{//if even, black
            color[0] = 0;
            color[1] = 0;
            color[2] = 0;
        }
        return color;
    }

    public static double[] smooth(Vector3 point){ //works bad for spheres
        double[] color = new double[3];

        //fract of coordinates
        double fx = 1 - Math.floor(point.getX());
        double fy = 1 - Math.floor(point.getY());
        double fz = 1 - Math.floor(point.getZ());

        //1 - abs(2*fract -1)
        double tx = 1 - Math.abs(2*fx - 1);
        double ty = 1 - Math.abs(2*fy - 1);
        double tz = 1 - Math.abs(2*fz - 1);
        color[0] = tx;
        color[1] = ty;
        color[2] = tz;
        return color;
    }

    public static double[] trippy(Vector3 point){
        double[] color = new double[3];

        //fract of coordinates
        double fx = point.getX()%1;
        double fy = point.getY()%1;
        double fz = point.getZ()%1;
        //1 - abs(2*fract -1)
        double tx = 1 - Math.abs(2*fx - 1);
        double ty = 1 - Math.abs(2*fy - 1);
        double tz = 1 - Math.abs(2*fz - 1);
        color[0] = tx;
        color[1] = ty;
        color[2] = tz;
        return color;
    }

    public static double[] marblePattern(Vector3 point) {
        double strength = 0.5;
        double[] color = noise.marble(strength, point);
        return color;
    }

    public static double[] woodPattern(Vector3 point, double amountOfRings) {
        double[] color = new double[3];
        double turbulenceFactor = 0.2;
        double rings = amountOfRings;
        // Calculate the distance value based on the 2D coordinates of the point
        double distValue = Math.sqrt(point.getX()*point.getX() + point.getY()*point.getY()) + 0.3 * noise.turbulence(turbulenceFactor, new Vector3(point.getX(), point.getY(), 0, "point"));
        // Calculate sine of the distance value to distort the rings
        double sineValue = 128.0 * Math.abs(Math.sin(2*rings*distValue*Math.PI - point.getZ()*point.getZ()));
        // Calculate the color value based on the sine value
        //R G B get different values (80, 30, 30). Was arbitrarily chosen. The difference between them makes sure the wood structure remains visible
        color[0] = (80 + sineValue)/255;
        color[1] = (30 + sineValue)/255;
        color[2] = 30/255;
        return color;
    }

    public static double[] testPattern(Vector3 point){
        double[][] noise = new double[128][128];
        for(int Y = 0; Y < 128; Y++){
            for(int X = 0; X < 128; X++){
                Random rand = new Random();
                noise[X][Y] = (rand.nextDouble() % 32768) / 32768.0;
            }
        }
        double[] color = new double[3];
        double x = point.getX();
        double y = point.getY();
        double fractX = x - (int) x;
        double fractY = y - (int) y;

        int x1 = ((int)x + 128)%128;
        int y1 = ((int)y + 128)%128;

        int x2 = (x1 + 128 - 1)%128;
        int y2 = (y1 + 128 - 1)%128;

        double value = 0.0;
        value += fractX * fractY * noise[y1][x1];
        value += fractX * (1 - fractY) * noise[y2][x1];
        value += (1 - fractX) * fractY * noise[y1][x2];
        value += (1 - fractX) * (1 - fractY) * noise[y2][x2];
        color[0] = value;
        color[1] = value;
        color[2] = value;
        return color;
    }



}
