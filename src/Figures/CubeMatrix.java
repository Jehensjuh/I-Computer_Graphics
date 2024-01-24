package Figures;

public class CubeMatrix {
    private int size;
    private double[][][] matrix;

    public CubeMatrix(int size) {
        this.size = size;
        matrix = new double[size][size][size];
        initializeCube();
    }

    private void initializeCube() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (int z = 0; z < size; z++) {
                    // Set the cube vertices to 1 (inside the cube) or 0 (outside)
                    if (x == 0 || x == size - 1 || y == 0 || y == size - 1 || z == 0 || z == size - 1) {
                        matrix[x][y][z] = 1.0;
                    } else {
                        matrix[x][y][z] = 0.0;
                    }
                }
            }
        }
    }

    public double[][][] getMatrix() {
        return matrix;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        int cubeSize = 5; // Adjust the cube size as needed
        CubeMatrix cube = new CubeMatrix(cubeSize);
        double[][][] cubeMatrix = cube.getMatrix();

        // Access and manipulate the cube matrix as needed
        for (int x = 0; x < cubeSize; x++) {
            for (int y = 0; y < cubeSize; y++) {
                for (int z = 0; z < cubeSize; z++) {
                    System.out.print(cubeMatrix[x][y][z] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
