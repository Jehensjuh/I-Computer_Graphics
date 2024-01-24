package Figures;

import MathStuff.Vector3;
import RayTracing.Intersection;
import RayTracing.Ray;

public abstract class Boolean extends Shape{
    protected String booleanOperation;
    @Override
    public double calculateNormal() {
        return 0;
    }

    @Override
    public boolean calculateIntersection(Ray ray, Intersection intersection) {
        return false;
    }

    @Override
    public boolean isPointOnShape(Vector3 point) {
        return false;
    }

    public void setBooleanOperation(String booleanOperation){
        this.booleanOperation = booleanOperation;
    }




}
