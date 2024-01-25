package Textures;

import MathStuff.Vector3;

public class SolidTextures {

    protected static Noise noise = new Noise();

    public static double[] checkerBoard(Vector3 point){
        double[] color = new double[3];
        if((Math.floor(point.getX()) + Math.floor(point.getY()) + Math.floor(point.getZ())) % 2 != 0){
            color[0] = 1;
            color[1] = 1;
            color[2] = 1;
        }else{
            color[0] = 0;
            color[1] = 0;
            color[2] = 0;
        }
        return color;
    }

    public static double[] marblePattern(Vector3 point) {
        double[] color = new double[3];

        // Parameters for the marble pattern
        double scale = 0.1; // Adjust the scale to control the frequency of the waves
        double turbulence = noise.latticeNoise(point.getX(),point.getY(), point.getZ()); // Adjust the turbulence to control the randomness of the waves
        double amplitude = 0.3;

        // Calculate marble pattern using sine functions
        double marbleValue = amplitude *Math.sin((point.getX() + turbulence * Math.sin(scale * point.getZ())) * scale);

        // Adjust the contrast of the marble pattern
        marbleValue = 0.5 + 0.5 * marbleValue;

        // Set the RGB color based on the marble pattern value
        color[0] = marbleValue;
        color[1] = marbleValue;
        color[2] = marbleValue;

        return color;
    }



}
