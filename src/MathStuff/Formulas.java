package MathStuff;

import Figures.Shape;
import RayTracing.Hit;
import RayTracing.Intersection;
import RayTracing.Ray;

public class Formulas {
    public static double[] getHeightAndWidth(double N, double Rho, double aspect){
        double height = N * Math.tan(Rho/2);
        double width = height * aspect;
        return new double[]{height, width};
    }

    public static int[] getRGB(Hit hit){
        int x1 = (int)( Math.abs( hit.hitPoint.getVector()[0])*100) % 256;
        int y1 = (int) (Math.abs( hit.hitPoint.getVector()[1])*100) % 256;
        int z1 = (int) (Math.abs( hit.hitPoint.getVector()[2])*100) % 256;
        int[] rgb = new int[]{x1,y1,z1};
        return rgb;
    }

    public static void setBasicTransformation(double scaleX, double scaleY, double scaleZ, double angle, double transX, double transY, double transZ, Shape shape){
        //for transformationmatrix first scale then rotation then translation, for inverse transformation matrix first translation then rotation then scale.
        double[][] transformationMatrix = MatrixOperations.multiplication(MatrixOperations.multiplication(Transformations.scaleMatrix(scaleX, scaleY, scaleZ),Transformations.xRotationMatrix(Math.toRadians(angle))),Transformations.translationMatrix(transX, transY, transZ));
        double[][] inverseTransformationMatrix = MatrixOperations.multiplication(MatrixOperations.multiplication(Transformations.inverseTranslationMatrix(transX,transY,transZ),Transformations.inversexRotationMatrix(Math.toRadians(angle))),Transformations.inverseScaleMatrix(scaleX,scaleY,scaleZ));
        shape.setTransform(transformationMatrix);
        shape.setInverseTransform(inverseTransformationMatrix);
    }

    public static double getUc(int i, double width, int cols){
        return (-width + (2 * width * i)/(cols));
    }
    public static double getVr(int j, double height, int rows){
        return (height - (2 * height * j)/(rows));
    }
    public static Vector3 getDir(double N, double Uc, double Vr){
            //System.out.println("N = "+N+" Uc = "+Uc+" Vr = "+Vr);
        return new Vector3(Uc, Vr, -N, "vector");
    }

    public static Ray applyInverseTransformation(double[][] matrix, Ray ray){
        //System.out.println("applying inverse transformation using: "+matrix[0][0]+" "+matrix[0][1]+" "+matrix[0][2]+" "+matrix[0][3]);
        //double[][] tempOrigin = ray.getOrigin().toMatrix();
        //double[][] tempDirection = ray.getDirection().toMatrix();
        Vector3 origin = ray.getOrigin();
        Vector3 direction = ray.getDirection();
        //System.out.println("old direction"+direction.toString());
        origin = Vector3.multiply(origin.getVector(),matrix,"point");
        direction = Vector3.multiply(direction.getVector(),matrix,"vector");
        //tempOrigin = MatrixOperations.multiplication(matrix, tempOrigin);
        //tempDirection = MatrixOperations.multiplication(matrix, tempDirection);
        //origin.returnToVector(tempOrigin);
        //System.out.println("new origin: "+origin.toString());
        //direction.returnToVector(tempDirection);
        //System.out.println("new direction: "+direction.toString());
        return new Ray(origin, direction);
    }

    public static Vector3 applyTransformation(double[][] matrix, Vector3 vector, String type){
        Vector3 newVector = Vector3.multiply(vector.getVector(),matrix,type);
        return newVector;
    }




}
