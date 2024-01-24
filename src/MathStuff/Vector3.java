package MathStuff;

public class Vector3 {
    private double x;
    private double y;
    private double z;
    private double w;
    private double[] vector;

    public Vector3(double x, double y, double z, String type){
        this.x = x;
        this.y = y;
        this.z = z;
        if(type == "point") {
            this.w = 1;
        }else if(type == "vector") {
            this.w = 0;
        }
        this.vector = new double[]{this.x, this.y, this.z, w};
    }
    public double[] getVector() {
        return vector;
    }

    public double[][] toMatrix(){
        double[][] matrix = {{this.x, 0, 0, 0},{0, this.y, 0, 0},{0, 0, this.z, 0},{0, 0, 0, this.w}};
        return matrix;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ(){
        return z;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    public static Vector3 normalise(Vector3 vector, String type){
        double magnitude = vector.getMagnitude();
        double[] normalisedVector = new double[]{vector.getVector()[0]/magnitude, vector.getVector()[1]/magnitude, vector.getVector()[2]/magnitude, vector.getVector()[3]/magnitude};
        return new Vector3(normalisedVector[0],normalisedVector[1],normalisedVector[2],type);
    }

    public Vector3 inverse(String type){
        return new Vector3(-this.x, -this.y, -this.z, type);
    }

    public static Vector3 addVector(Vector3 vector1, Vector3 vector2, String type){
        double[] vector = new double[4];
        for(int i = 0; i < 4; i++){
            vector[i] = vector1.getVector()[i] + vector2.getVector()[i];
        }
        return new Vector3(vector[0], vector[1], vector[2], type);
    }

    public void addVector(Vector3 vector){
        this.x += vector.getVector()[0];
        this.y += vector.getVector()[1];
        this.z += vector.getVector()[2];
    }

    public void returnToVector(double[][] matrix){
        double[] vector = {matrix[0][0], matrix[1][1], matrix[2][2], matrix[3][3]};
        this.vector = vector;
    }
    @Override
    public String toString(){
        return "x = "+this.x+" y = "+this.y+" z = "+this.z+" w = "+this.w;
    }

    public static double[] subtract(double[] vector1, double[] vector2) {
        double[] result = new double[vector1.length];
        for(int i = 0; i < vector1.length; i++) {
            result[i] = vector1[i] - vector2[i];
        }
        return result;
    }

    public double getMagnitude(){
        return Math.sqrt((this.x*this.x)+(this.y*this.y)+(this.z*this.z));
    }

    public static Vector3 subtractVector(Vector3 vector1, Vector3 vector2, String type){
        double[] result = new double[vector1.getVector().length];
        for(int i = 0; i < vector1.getVector().length; i++) {
            result[i] = vector1.getVector()[i] - vector2.getVector()[i];
        }
        return new Vector3(result[0],result[1],result[2],type);
    }

    public static double dotProduct(Vector3 vector1, Vector3 vector2){
        return (vector1.getVector()[0]*vector2.getVector()[0])+(vector1.getVector()[1]*vector2.getVector()[1])+(vector1.getVector()[2]*vector2.getVector()[2]);
    }

    public static double dotProd(double[] vector1, double[] vector2) {
        double result = 0;
        for(int i = 0; i < vector1.length; i++) {
            result += vector1[i] * vector2[i];
        }
        return result;
    }

    public static Vector3 multiply(double[] vector,double[][] matrix, String type){
        double[] result = new double[]{(matrix[0][0]*vector[0])+(matrix[0][1]*vector[1])+(matrix[0][2]*vector[2])+(matrix[0][3]*vector[3]),(matrix[1][0]*vector[0])+(matrix[1][1]*vector[1])+(matrix[1][2]*vector[2])+(matrix[1][3]*vector[3]),(matrix[2][0]*vector[0])+(matrix[2][1]*vector[1])+(matrix[2][2]*vector[2])+(matrix[2][3]*vector[3]),(matrix[3][0]*vector[0])+(matrix[3][1]*vector[1])+(matrix[3][2]*vector[2])+(matrix[3][3]*vector[3])};
        return new Vector3(result[0],result[1],result[2],type);
    }

    public void scalarMultiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
    }

    public static Vector3 scalarVectorMultiply(double scalar, Vector3 vector, String type) {
        double[] result = new double[]{vector.getVector()[0]*scalar, vector.getVector()[1]*scalar, vector.getVector()[2]*scalar, vector.getVector()[3]*scalar};
        return new Vector3(result[0],result[1],result[2],type);
    }
}
