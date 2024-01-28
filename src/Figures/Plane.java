package Figures;

import MathStuff.Formulas;
import MathStuff.Vector3;
import RayTracing.Hit;
import RayTracing.Intersection;
import RayTracing.PhongMaterials;
import RayTracing.Ray;

public class Plane extends Shape{
    private double distance;//the distance from the origin (D)
    public Plane() {
        this.normal = new Vector3(0,0,1, "vector"); //a vector perpendicular to the plane
        this.origin = new Vector3(0,0,0,"point"); //a point on the plane
        this.distance = 0;//the distance from the origin (D)
        this.inverseTransformMatrix = null;
        this.transformMatrix = null;
        this.color = new Vector3(0,255,0,"vector");
        this.material = PhongMaterials.brass();
        this.setPriority(5);
    }

/*    @Override
    public double calculateNormal() {
        return 0;
    }

    @Override
    public boolean calculateIntersection(Ray rayer, Intersection inter) {
    Ray ray = rayer;
        if (this.inverseTransformMatrix != null) {
            // Apply the inverse transformation to the ray
            ray = Formulas.applyInverseTransformation(this.inverseTransformMatrix, ray);
        }

        // Assuming the plane is aligned with the z-axis and centered at the origin
        double t = -ray.getOrigin().getVector()[1] / ray.getDirection().getVector()[1];
        if (t > 0.0001) {
            // Valid intersection point
            Hit hit = new Hit();
            hit.hitTime = t;
            hit.hitShape = this;
            hit.isEntering = true;  // Assuming the plane is one-sided
            hit.surface = 0;

            Vector3 point = new Vector3(
                    ray.getOrigin().getVector()[0] + t * ray.getDirection().getVector()[0],
                    ray.getOrigin().getVector()[1] + t * ray.getDirection().getVector()[1],
                    ray.getOrigin().getVector()[2] + t * ray.getDirection().getVector()[2],
                    "point"
            );

            hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
            hit.normal = calculatePlaneNormal(hit.isEntering);
            hit.usedRay = ray;

            inter.hits.add(hit);
            return true;
        }

        return false;
    }

    public Vector3 calculatePlaneNormal(boolean isEntering) {
        // Assuming the plane is aligned with the z-axis
        double sign = isEntering ? 1.0 : -1.0;
        return new Vector3(0, sign, 0, "vector");
    }

    @Override
    public boolean isPointOnShape(Vector3 point) {
        double temp = this.normal.getVector()[0]*point.getVector()[0]+this.normal.getVector()[1]*point.getVector()[1]+this.normal.getVector()[2]*point.getVector()[2]+this.distance;
        if(temp == 0){
            return true;
        }else {
            return false;
        }
    }*/
@Override
public double calculateNormal() {
    return 0;
}

    @Override
    public boolean calculateIntersection(Ray rayer, Intersection intersection) {
        Ray ray = rayer;
        /*if(this.inverseTransformMatrix != null){
            //we apply the inverse transformation to the ray
            ray = Formulas.applyInverseTransformation(this.inverseTransformMatrix, ray);
        }
        double denom = ray.getDirection().getVector()[2];//z value
        if(Math.abs(denom)<0.0001){ //if the ray is parallel to the plane
            return false;
        }
        double q = (ray.getOrigin().getVector()[2]);
        double time = -q/denom;//hit time
        if(time <= 0.0){
            return false; //it lies behind the eye
        }
        double hx = ray.getOrigin().getVector()[0] + time*ray.getDirection().getVector()[0];//x value of the hit point
        double hy = ray.getOrigin().getVector()[1] + time*ray.getDirection().getVector()[1];//y value of the hit point

        Hit hit = new Hit();
        hit.hitTime = time;
        hit.hitShape = this;
        hit.isEntering = true;
        hit.surface = 0;
        Vector3 point = new Vector3(hx,hy,0,"point");
        hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
        Vector3 temp = chooseNormal(q);
        hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
        hit.usedRay = ray;
        intersection.hits.add(hit);

        return true;*/
        if (this.inverseTransformMatrix != null) {
            // we apply the inverse transformation to the ray
            ray = Formulas.applyInverseTransformation(this.inverseTransformMatrix, ray);
        }

        double denom = Vector3.dotProduct(this.normal, ray.getDirection());

        if (Math.abs(denom) < 0.0001) { // if the ray is parallel to the plane
            return false;
        }

        double q = Vector3.dotProduct(Vector3.subtractVector(ray.getOrigin(), this.origin, "point"), this.normal);
        double time = -q / denom; // hit time

        if (time <= 0.0) {
            return false; // it lies behind the eye
        }
        double hx = ray.getOrigin().getVector()[0] + time*ray.getDirection().getVector()[0];//x value of the hit point
        double hy = ray.getOrigin().getVector()[1] + time*ray.getDirection().getVector()[1];//y value of the hit point
        double hz = ray.getOrigin().getVector()[2] + time*ray.getDirection().getVector()[2];//z value of the hit point
        Hit hit = new Hit();
        hit.hitTime = time;
        hit.hitShape = this;
        hit.isEntering = true;
        hit.surface = 0;
        Vector3 point = new Vector3(hx,hy,hz,"point");
        hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
        Vector3 temp = chooseNormal(q);
        hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
        hit.usedRay = ray;
        intersection.hits.add(hit);

        return true;
    }

    private Vector3 chooseNormal(double q){
        if(q > 0.0){
            return new Vector3(0,0,1,"vector");
        }else{
            return new Vector3(0,0,-1,"vector");
        }
    }
    @Override
    public String toString(){
        return "Square";
    }

    @Override
    public boolean isPointOnShape(Vector3 point) {
        return false;
    }
}
