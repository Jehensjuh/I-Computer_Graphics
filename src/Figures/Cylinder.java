package Figures;

import MathStuff.Formulas;
import MathStuff.Vector3;
import RayTracing.Hit;
import RayTracing.Intersection;
import RayTracing.PhongMaterials;
import RayTracing.Ray;

public class Cylinder extends Shape{

    public Cylinder(){
        this.material = PhongMaterials.brass();
        this.inverseTransformMatrix = null;
        this.transformMatrix = null;
        this.setPriority(4);
    }
    @Override
    public double calculateNormal() {
        return 0;
    }

    @Override
    public boolean calculateIntersection(Ray ray, Intersection intersection) {
        if(this.inverseTransformMatrix != null){
            //we apply the inverse transformation to the ray
            ray = Formulas.applyInverseTransformation(this.inverseTransformMatrix, ray);
        }

        Vector3 rayOrigin = ray.getOrigin();
        Vector3 rayDirection = ray.getDirection();

        double a = rayDirection.getVector()[0]*rayDirection.getVector()[0] + rayDirection.getVector()[1]*rayDirection.getVector()[1];
        double b = 2*(rayOrigin.getVector()[0]*rayDirection.getVector()[0] + rayOrigin.getVector()[1]*rayDirection.getVector()[1]);
        double c = rayOrigin.getVector()[0]*rayOrigin.getVector()[0] + rayOrigin.getVector()[1]*rayOrigin.getVector()[1] - 1;

        double discrim = b*b - 4*a*c;

        if(discrim <0.0001){
            return false;
        } else {
            double discrimSqrt = Math.sqrt(discrim);
            double t1 = (-b - discrimSqrt)/(2*a);
            double t2 = (-b + discrimSqrt)/(2*a);

            //check if the intersection is within a valid range
            if(t1 > 0.0001 && Math.abs(rayOrigin.getVector()[2] + t1 * rayDirection.getVector()[2]) <= 1){
                Hit hit = new Hit();
                hit.hitShape = this;
                hit.hitTime = t1;
                hit.isEntering = true;
                hit.surface = 0;
                Vector3 point = new Vector3(rayOrigin.getVector()[0] + t1*rayDirection.getVector()[0], rayOrigin.getVector()[1] + t1*rayDirection.getVector()[1], rayOrigin.getVector()[2] + t1*rayDirection.getVector()[2], "point");
                hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
                Vector3 temp = new Vector3(point.getVector()[0], point.getVector()[1], 0, "point");
                hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
                hit.usedRay = ray;
                intersection.hits.add(hit);
            }
            if(t2 > 0.0001 && Math.abs(rayOrigin.getVector()[2] + t2 * rayDirection.getVector()[2]) <= 1){
                Hit hit = new Hit();
                hit.hitShape = this;
                hit.hitTime = t2;
                hit.isEntering = false;
                hit.surface = 0;
                Vector3 point = new Vector3(rayOrigin.getVector()[0] + t2*rayDirection.getVector()[0], rayOrigin.getVector()[1] + t2*rayDirection.getVector()[1], rayOrigin.getVector()[2] + t2*rayDirection.getVector()[2], "point");
                hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
                Vector3 temp = new Vector3(point.getVector()[0], point.getVector()[1], 0, "point");
                hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
                hit.usedRay = ray;
                intersection.hits.add(hit);
            }
        }
        checkCapsIntersection(intersection, ray);
        return !intersection.hits.isEmpty();
    }

    private void checkCapsIntersection(Intersection inter, Ray ray){
        Vector3 rayOrigin = ray.getOrigin();
        Vector3 rayDirection = ray.getDirection();
        double tBottom = (-rayOrigin.getVector()[2]-1)/rayDirection.getVector()[2];
        if(tBottom > 0.0001){
            double x = rayOrigin.getVector()[0] + tBottom * rayDirection.getVector()[0];
            double y = rayOrigin.getVector()[1] + tBottom * rayDirection.getVector()[1];
            if(x*x + y*y <=1){
                Hit hit = new Hit();
                hit.hitShape = this;
                hit.hitTime = tBottom;
                hit.isEntering = true;
                hit.surface = 1;
                Vector3 point = new Vector3(rayOrigin.getVector()[0] + tBottom*rayDirection.getVector()[0], rayOrigin.getVector()[1] + tBottom*rayDirection.getVector()[1], rayOrigin.getVector()[2] + tBottom*rayDirection.getVector()[2], "point");
                hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
                Vector3 temp = new Vector3(0, 0, -1, "point");
                hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
                hit.usedRay = ray;
                inter.hits.add(hit);
            }
        }
        double tTop = (1 - rayOrigin.getVector()[2])/rayDirection.getVector()[2];
        if(tTop > 0.0001){
            double x = rayOrigin.getVector()[0] + tTop * rayDirection.getVector()[0];
            double y = rayOrigin.getVector()[1] + tTop * rayDirection.getVector()[1];
            if(x*x + y*y <=1){
                Hit hit = new Hit();
                hit.hitShape = this;
                hit.hitTime = tTop;
                hit.isEntering = false;
                hit.surface = 1;
                Vector3 point = new Vector3(rayOrigin.getVector()[0] + tTop*rayDirection.getVector()[0], rayOrigin.getVector()[1] + tTop*rayDirection.getVector()[1], rayOrigin.getVector()[2] + tTop*rayDirection.getVector()[2], "point");
                hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
                Vector3 temp = new Vector3(0, 0, 1, "point");
                hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
                hit.usedRay = ray;
                inter.hits.add(hit);
            }
        }
    }

    @Override
    public boolean isPointOnShape(Vector3 point) {
        return false;
    }
}
