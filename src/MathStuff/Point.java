package MathStuff;

public class Point {
    private double x;
    private double y;
    private double z;
    private Vector v;

    public Point(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
        this.v = new Vector(x,y,z,1.0);
    }
}
