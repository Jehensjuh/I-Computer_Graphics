package Figures;

import MathStuff.Formulas;
import MathStuff.Vector3;
import RayTracing.*;

public class Sphere extends Shape {
    private double radius;

    public Sphere(Vector3 normal) {
        this.normal = normal; //a vector perpendicular to the plane
        this.origin = new Vector3(0,0,0,"point"); //a point on the plane
        this.radius = 1;//the distance from the origin (D)
        this.inverseTransformMatrix = null;
        this.transformMatrix = null;
        this.color = new Vector3(50,0,0,"vector");
        this.material = PhongMaterials.brass();
        this.setPriority(1);
    }

    @Override
    public double calculateNormal() {
        return 0;
    }

    @Override
    public boolean calculateIntersection(Ray rayer, Intersection inter) {
        Ray ray = rayer;
        //System.out.println("rayer direction: " + rayer.getDirection().toString());
        if(this.inverseTransformMatrix != null){
            //we apply the inverse transformation to the ray
            ray = Formulas.applyInverseTransformation(this.inverseTransformMatrix, ray);
            //System.out.println("ray direction: " + ray.getDirection().toString());
        }
        double[] oc = Vector3.subtractVector(ray.getOrigin(), this.origin,"point").getVector();
        Vector3 rayOrigin = Vector3.subtractVector(ray.getOrigin(), this.origin,"point");
        Vector3 rayDirection = ray.getDirection();

        double A = Vector3.dotProduct(rayDirection, rayDirection);
        double B = Vector3.dotProduct(rayOrigin, rayDirection);
        double Ct= rayOrigin.getMagnitude() -1;
        double C = Vector3.dotProduct(rayOrigin, rayOrigin) -1;

        double discrim = (B * B) - (A * C);
        //System.out.println("discrim: " + discrim);
        int num = 0; //amount of hits so far
        if (discrim < 0.0001) {
            //System.out.println("No intersection");
            return false;
        }else{
            //System.out.println("hit");
            double discrimSQRT = Math.sqrt(discrim);
            double t1 = (-B - discrimSQRT) / A;//earlier hit
            double t2 = (-B + discrimSQRT) / A;//later hit
            if (t1 > 0.0001) {//if t1 is negative, the intersection is behind the ray, if it's positive it will be the smallest t and thus the first hit
                Hit hit = new Hit();
                hit.hitTime = t1;
                hit.hitShape = this;
                hit.isEntering = true;
                hit.surface = 0;
                Vector3 point = new Vector3(rayOrigin.getVector()[0] + t1 * rayDirection.getVector()[0], rayOrigin.getVector()[1] + t1 * rayDirection.getVector()[1], rayOrigin.getVector()[2] + t1 * rayDirection.getVector()[2], "point");
                hit.hitPoint = Formulas.applyTransformation(transformMatrix, point, "point");
                hit.normal = point;
                hit.usedRay = ray;
                inter.hits.add(hit);

                num = 1; //hit
            }else{
                //System.out.println("t1 is negative");
            }
            if (t2 > 0.0001) {//if t2 is negative, the intersection is behind the ray, if it's positive it will be the smallest t and thus the first hit
                Hit hit = new Hit();
                hit.hitTime = t2;
                hit.hitShape = this;
                hit.isEntering = false;
                hit.surface = 0;
                Vector3 point = new Vector3(rayOrigin.getVector()[0] + t2 * rayDirection.getVector()[0], rayOrigin.getVector()[1] + t2 * rayDirection.getVector()[1], rayOrigin.getVector()[2] + t2 * rayDirection.getVector()[2], "point");
                hit.hitPoint = Formulas.applyTransformation(transformMatrix, point,"point");
                hit.normal = point;
                hit.usedRay = ray;
                inter.hits.add(hit);

                num++; //hit
            }else{
                //System.out.println("t2 is negative");
            }
            if(num > 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return "Sphere";
    }

    @Override
    public boolean isPointOnShape(Vector3 point){
        double temp = this.normal.getVector()[0]*point.getVector()[0]+this.normal.getVector()[1]*point.getVector()[1]+this.normal.getVector()[2]*point.getVector()[2]-this.radius*this.radius;
        if(temp == 0)
            return true;
        else
            return false;
    }
    //represents a sphere with radius 1 in a 3x3 matrix
}
