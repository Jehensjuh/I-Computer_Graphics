package Figures;

import MathStuff.Formulas;
import MathStuff.Vector3;
import RayTracing.*;

public class Square extends Shape{
//square in the z=0 plane
    public Square(){
        this.origin = new Vector3(0,0,0,"point");
        this.normal = new Vector3(0,0,1,"vector");
        this.inverseTransformMatrix = new double[4][4];
        this.transformMatrix = new double[4][4];
        this.color = new Vector3(0,255,0,"vector");
        this.material = PhongMaterials.brass();
        this.setPriority(2);
    }
    @Override
    public double calculateNormal() {
        return 0;
    }

    @Override
    public boolean calculateIntersection(Ray rayer, Intersection intersection) {
        Ray ray = rayer;
        if(this.inverseTransformMatrix != null){
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
        if((hx > 1.0)||(hx < -1.0)) return false; // misses in x-dimension
        if((hy > 1.0)||(hy < -1.0)) return false; // misses in y-dimension
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
