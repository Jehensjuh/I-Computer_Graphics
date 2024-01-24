package Figures;

import MathStuff.Formulas;
import MathStuff.Vector3;
import RayTracing.Hit;
import RayTracing.Intersection;
import RayTracing.PhongMaterials;
import RayTracing.Ray;

public class FailedShape extends Shape{
    double s;
    public FailedShape(double s){
        this.s = s;
        this.material = PhongMaterials.brass();
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
        int num = 0;

        //intersections with cylinder wall
        double d = (s-1)*ray.getDirection().getVector()[2];
        double f = 1 + (s-1)*ray.getOrigin().getVector()[2];
        double a  = ray.getDirection().getVector()[0]*ray.getDirection().getVector()[0] + ray.getDirection().getVector()[1]*ray.getDirection().getVector()[1] - d*d;
        double b = 2*(ray.getOrigin().getVector()[0]*ray.getDirection().getVector()[0] + ray.getOrigin().getVector()[1]*ray.getDirection().getVector()[1] - d*f);
        double c = ray.getOrigin().getVector()[0]*ray.getOrigin().getVector()[0] + ray.getOrigin().getVector()[1]*ray.getOrigin().getVector()[1] - f*f;
        double discrim = b*b - a*c;

        if(discrim >= 0.0001){
            double discRoot = Math.sqrt(discrim);
            double t1 = (-b - discRoot)/a;
            double t2 = (-b + discRoot)/a;

            if(t1 > 0.0001){
                Vector3 point = new Vector3(ray.getOrigin().getVector()[0] + t1*ray.getDirection().getVector()[0], ray.getOrigin().getVector()[1] + t1*ray.getDirection().getVector()[1], ray.getOrigin().getVector()[2] + t1*ray.getDirection().getVector()[2], "point");
                if((point.getVector()[2] > 0)&&(point.getVector()[2] < 1.0)){
                    Hit hit = new Hit();
                    hit.hitTime = t1;
                    hit.hitShape = this;
                    hit.isEntering = true;
                    hit.surface = 0;
                    hit.hitPoint = point;
                    hit.normal = normal(point);
                    hit.usedRay = ray;
                    intersection.hits.add(hit);
                    num++;
                }
            }
            if(t2 > 0.0001){
                Vector3 point = new Vector3(ray.getOrigin().getVector()[0] + t2*ray.getDirection().getVector()[0], ray.getOrigin().getVector()[1] + t2*ray.getDirection().getVector()[1], ray.getOrigin().getVector()[2] + t2*ray.getDirection().getVector()[2], "point");
                if((point.getVector()[2] > 0)&&(point.getVector()[2] < 1.0)){
                    Hit hit = new Hit();
                    hit.hitTime = t2;
                    hit.hitShape = this;
                    hit.isEntering = false;
                    hit.surface = 0;
                    hit.hitPoint = point;
                    hit.normal = normal(point);
                    hit.usedRay = ray;
                    intersection.hits.add(hit);
                    num++;
                }
            }
        }
        Vector3 tempNormal = new Vector3(0,0,0,"vector");
        //hits with base planes
        if(Math.abs(ray.getDirection().getVector()[2])>=0.00001){//avoid parallel with base => no intersect
            //hit with z = 0;
            double hitTime = -ray.getOrigin().getVector()[2]/ray.getDirection().getVector()[2];
            if(hitTime >= 0.0001){
                Vector3 point = new Vector3(ray.getOrigin().getVector()[0] + hitTime*ray.getDirection().getVector()[0], ray.getOrigin().getVector()[1] + hitTime*ray.getDirection().getVector()[1], ray.getOrigin().getVector()[2] + hitTime*ray.getDirection().getVector()[2], "point");
                if((Vector3.dotProduct(point,point)) <= 1.0){
                    Hit hit = new Hit();
                    hit.hitTime = hitTime;
                    hit.hitShape = this;
                    hit.isEntering = Vector3.dotProduct(ray.getDirection(), normal(point)) > 0;//if normal & dir in same direction, dot product is positive and ray is leaving
                    hit.surface = 1;
                    hit.hitPoint = point;
                    hit.normal = Formulas.applyTransformation(transformMatrix, new Vector3(0,0,-1,"vector"),"vector");
                    hit.usedRay = ray;
                    intersection.hits.add(hit);
                    num++;
                }
            }
            //hit with z = 1;
            hitTime = (1-ray.getOrigin().getVector()[2])/ray.getDirection().getVector()[2];
            if(hitTime >= 0.0001){
                Vector3 point = new Vector3(ray.getOrigin().getVector()[0] + hitTime*ray.getDirection().getVector()[0], ray.getOrigin().getVector()[1] + hitTime*ray.getDirection().getVector()[1], ray.getOrigin().getVector()[2] + hitTime*ray.getDirection().getVector()[2], "point");
                if((Vector3.dotProduct(point,point)) -1 < s*s){//p = (x,y,1,1), p.dot doesn't use last value -> x² + y² < s² correct for '1' term
                    Hit hit = new Hit();
                    hit.hitTime = hitTime;
                    hit.hitShape = this;
                    hit.isEntering = Vector3.dotProduct(ray.getDirection(), normal(point)) > 0;//if normal & dir in same direction, dot product is positive and ray is leaving
                    hit.surface = 2;
                    hit.hitPoint = point;
                    hit.normal = Formulas.applyTransformation(transformMatrix, new Vector3(0,0,1,"vector"),"vector");
                    hit.usedRay = ray;
                    intersection.hits.add(hit);
                    num++;
                }
            }
        }
        //sort hits:
        if(num >= 2){
            if(intersection.hits.get(0).hitTime > intersection.hits.get(1).hitTime){
                Hit temp = intersection.hits.get(0);
                intersection.hits.set(0, intersection.hits.get(1));
                intersection.hits.set(1, temp);
            }
            intersection.hits.get(0).isEntering = true;
            intersection.hits.get(1).isEntering = false;
        }
        if(num == 1){
            intersection.hits.get(0).isEntering = false; //only way to have single intersecction is a ray that starts inside the object
        }
        if(num == 2){
            if(Math.abs(intersection.hits.get(0).hitTime - intersection.hits.get(1).hitTime) < 0.00001){
                //thin material
            }
        }
        return(num>0);
    }

    public Vector3 normal(Vector3 point){
        if (point.getVector()[2] == 0) return new Vector3(0,0,-1,"vector");//base plane
        if (point.getVector()[2] == 1) return new Vector3(0,0,1,"vector");//top plane
        Vector3 norm = new Vector3(point.getVector()[0],point.getVector()[1], -1*(s-1)*(1+(s-1)*point.getVector()[2]),"vector");
        norm = Formulas.applyTransformation(transformMatrix, norm,"vector");
        return norm;
    }


    @Override
    public boolean isPointOnShape(Vector3 point) {
        return false;
    }
}
