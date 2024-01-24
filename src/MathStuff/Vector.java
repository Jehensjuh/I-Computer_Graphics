package MathStuff;


public class Vector {
    private double[] v;

    public Vector(double ... v){ //you can either give an array or list the contents of the array (array vs 1,2,3)
        this.v = new double[v.length];
            for(int i = 0; i<v.length;i++){
                this.v[i] = v[i];
            }
    }
    @Override
    public String toString(){
        String str = "[";
        String sep = ",\n";

        for (int i = 0; i < this.v.length; i++){
            str += this.v[i];

            if (i < (this.v.length -1)){
                str += sep;
            }
        }
            return str + "]";
    }


    public double get(int position){
        return this.v[position];
    }
    public int length(){
        return this.v.length;
    }

    public double[] getV(){
        return this.v;
    }

    public void setV(double[] v){
        this.v = v;
    }

    public void set(int index, double value){
        this.v[index] = value;
    }

    public boolean isZero(){
        int sum = 0;
        for (int i = 0; i < this.v.length; i++){
            if(v[i]!=0){
                sum++;
            }
        }
        if(sum == 0){
            return true;
        }else{
            return false;
        }
    }

    public static void checkLengths(Vector u1, Vector u2) {
        if (u1.length() != u2.length()) {
            throw new IllegalArgumentException(
                    "Vectors are different lengths");
        }
    }

    public Vector add(Vector u) {
        return Vector.sum(this, u);
    }public static Vector sum(Vector u1, Vector u2) {
        Vector.checkLengths(u1, u2); // ** see comment

        double[] sums = new double[u1.length()];

        for (int i = 0; i < sums.length; i++) {
            sums[i] = u1.get(i) + u2.get(i);
        }

        return new Vector(sums);
    }

    public Vector multiply(double scalar) {
        return Vector.product(this, scalar);
    }public static Vector product(Vector u, double scalar) {
        double[] products = new double[u.length()];

        for (int i = 0; i < products.length; i++) {
            products[i] = scalar * u.get(i);
        }

        return new Vector(products);
    }

    public double dot(Vector u) {
        return Vector.dotProduct(this, u);
    }

    public static double dotProduct(Vector u1, Vector u2) {
        Vector.checkLengths(u1, u2);

        double sum = 0;

        for (int i = 0; i < u1.length(); i++) {
            sum += (u1.get(i) * u2.get(i));
        }

        return sum;
    }

    public Vector cross(Vector u) {
        return Vector.crossProduct(this, u);
    }


    public static Vector crossProduct(Vector a, Vector b) {
        // check to make sure both vectors are the right length
        if (a.length() != 3) {
            throw new IllegalArgumentException("Invalid vector length (first vector)");
        }
        if (a.length() != 3) {
            throw new IllegalArgumentException("Invalid vector length (second vector)");
        }
        Vector.checkLengths(a, b); // just in case

        double[] entries = new double[] {
                a.v[1] * b.v[2] - a.v[2] * b.v[1],
                a.v[2] * b.v[0] - a.v[0] * b.v[2],
                a.v[0] * b.v[1] - a.v[1] * b.v[0]};

        return new Vector(entries);
    }

    // static method
    public static double pnorm(Vector u, double p) {
        if (p < 1) {
            throw new IllegalArgumentException("p must be >= 1");
        }

        double sum = 0;

        for (int i = 0; i < u.length(); i++) {
            sum += Math.pow(Math.abs(u.get(i)), p);
        }

        return Math.pow(sum, 1/p);
    }

    // magnitude
    public double magnitude() {
        return Vector.pnorm(this, 2);
    }

    public static Vector normalize(Vector v) {
        if (v.isZero()) {
            throw new IllegalArgumentException();
        } else {
            return v.multiply(1.0/v.magnitude());
        }
    }


}
