package MathStuff;

public class MatrixOperations {

    public static double[][] multiplication(double[][] matrix1, double[][] matrix2){
        double[][] result = new double[matrix1.length][matrix2[0].length];
        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix2[0].length; j++){
                for(int k = 0; k < matrix1[0].length; k++){
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    public static double[][] addition(double[][] matrix1, double[][] matrix2){
        double[][] result = new double[matrix1.length][matrix1[0].length];
        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix1[0].length; j++){
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    public static double[][] subtraction(double[][] matrix1, double[][] matrix2){
        double[][] result = new double[matrix1.length][matrix1[0].length];
        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix1[0].length; j++){
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return result;
    }

    public static double[][] transpose(double[][] matrix){
        return new double[][]{{matrix[0][0], matrix[1][0], matrix[2][0], matrix[3][0]},
                {matrix[0][1], matrix[1][1], matrix[2][1], matrix[3][1]},
                {matrix[0][2], matrix[1][2], matrix[2][2], matrix[3][2]},
                {matrix[0][3], matrix[1][3], matrix[2][3], matrix[3][3]}};
    }

}
