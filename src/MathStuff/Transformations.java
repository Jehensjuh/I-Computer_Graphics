package MathStuff;

public class Transformations {
    public static double[][] translationMatrix(double x, double y, double z) {

        double[][] matrix = new double[][]{{1, 0, 0, x}, {0, 1, 0, y}, {0, 0, 1, z}, {0, 0, 0, 1}};
        return matrix;
    }
    public static double[][] inverseTranslationMatrix(double x, double y, double z) {
        double[][] matrix = new double[][]{{1, 0, 0, -x}, {0, 1, 0, -y}, {0, 0, 1, -z}, {0, 0, 0, 1}};
        return matrix;
    }

    public static double[][] scaleMatrix(double x, double y, double z) {
        double[][] matrix = new double[][]{{x, 0, 0, 0}, {0, y, 0, 0}, {0, 0, z, 0}, {0, 0, 0, 1}};
        return matrix;
    }

    public static double[][] inverseScaleMatrix(double x, double y, double z) {
        double[][] matrix = new double[][]{{1/x, 0, 0, 0}, {0, 1/y, 0, 0}, {0, 0, 1/z, 0}, {0, 0, 0, 1}};
        return matrix;
    }

    public static double[][] xRotationMatrix(double theta) {
        double[][] matrix = new double[][]{{1, 0, 0, 0}, {0, Math.cos(theta), -Math.sin(theta), 0}, {0, Math.sin(theta), Math.cos(theta), 0}, {0, 0, 0, 1}};
        return matrix;
    }

    public static double[][] inversexRotationMatrix(double theta) {
        double[][] matrix = new double[][]{{1, 0, 0, 0}, {0, Math.cos(theta), Math.sin(theta), 0}, {0, -Math.sin(theta), Math.cos(theta), 0}, {0, 0, 0, 1}};
        return matrix;
    }

    public static double[][] yRotationMatrix(double theta) {
        double[][] matrix = new double[][]{{Math.cos(theta), 0, Math.sin(theta), 0}, {0, 1, 0, 0}, {-Math.sin(theta), 0, Math.cos(theta), 0}, {0, 0, 0, 1}};
        return matrix;
    }

    public static double[][] inverseyRotationMatrix(double theta) {
        double[][] matrix = new double[][]{{Math.cos(theta), 0, -Math.sin(theta), 0}, {0, 1, 0, 0}, {Math.sin(theta), 0, Math.cos(theta), 0}, {0, 0, 0, 1}};
        return matrix;
    }

    public static double[][] zRotationMatrix(double theta) {
        double[][] matrix = new double[][]{{Math.cos(theta), -Math.sin(theta), 0, 0}, {Math.sin(theta), Math.cos(theta), 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        return matrix;
    }

    public static double[][] inversezRotationMatrix(double theta) {
        double[][] matrix = new double[][]{{Math.cos(theta), Math.sin(theta), 0, 0}, {-Math.sin(theta), Math.cos(theta), 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        return matrix;
    }

    public static double[][] xShearMatrix(double y, double z) {
        double[][] matrix = new double[][]{{1, y, z, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0,0,0,1}};
        return matrix;
    }

    public static double[][] inversexShearMatrix(double y, double z) {
        double[][] matrix = new double[][]{{1, -y, -z, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0,0,0,1}};
        return matrix;
    }

    public static double[][] yShearMatrix(double x, double z) {
        double[][] matrix = new double[][]{{1, 0, 0, 0}, {x, 1, z, 0}, {0, 0, 1, 0}, {0,0,0,1}};
        return matrix;
    }

    public static double[][] inverseyShearMatrix(double x, double z) {
        double[][] matrix = new double[][]{{1, 0, 0, 0}, {-x, 1, -z, 0}, {0, 0, 1, 0}, {0,0,0,1}};
        return matrix;
    }

    public static double[][] zShearMatrix(double x, double y) {
        double[][] matrix = new double[][]{{1, 0, 0, 0}, {0, 1, 0, 0}, {x, y, 1, 0}, {0,0,0,1}};
        return matrix;
    }

    public static double[][] inversezShearMatrix(double x, double y) {
        double[][] matrix = new double[][]{{1, 0, 0, 0}, {0, 1, 0, 0}, {-x, -y, 1, 0}, {0,0,0,1}};
        return matrix;
    }
}
