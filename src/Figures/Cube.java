package Figures;

import MathStuff.Formulas;
import MathStuff.Vector3;
import RayTracing.*;

public class Cube extends Shape {

    public Cube(Vector3 origin, Vector3 normal) {
        this.origin = origin;
        this.normal = normal;
        this.inverseTransformMatrix = null;
        this.transformMatrix = null;
        this.color = new Vector3(0,0,50,"vector");
        this.material = PhongMaterials.brass();
        this.setPriority(3);
    }
    @Override
    public double calculateNormal() {
        return 0;
    }

    @Override
    public boolean calculateIntersection(Ray rayer, Intersection intersection) {
        Ray ray = rayer;
        double tHit = 0, numerator = 0, denominator = 0;
        double tIn = -100000.0, tOut = 100000.0;//plus minus infinity
        int inSurface = 0, outSurface = 0;//which of the six surfaces?
        if(this.inverseTransformMatrix != null){
            //we apply the inverse transformation to the ray
            ray = Formulas.applyInverseTransformation(this.inverseTransformMatrix, ray);
        }
        for(int i =0; i < 6; i++){
            switch(i){
                case 0:
                    numerator = 1.0 - ray.getOrigin().getVector()[1];
                    denominator = ray.getDirection().getVector()[1];
                    break;
                case 1:
                    numerator = 1.0 + ray.getOrigin().getVector()[1];
                    denominator = -ray.getDirection().getVector()[1];
                    break;
                case 2:
                    numerator = 1.0 - ray.getOrigin().getVector()[0];
                    denominator = ray.getDirection().getVector()[0];
                    break;
                case 3:
                    numerator = 1.0 + ray.getOrigin().getVector()[0];
                    denominator = -ray.getDirection().getVector()[0];
                    break;
                case 4:
                    numerator = 1.0 - ray.getOrigin().getVector()[2];
                    denominator = ray.getDirection().getVector()[2];
                    break;
                case 5:
                    numerator = 1.0 + ray.getOrigin().getVector()[2];
                    denominator = -ray.getDirection().getVector()[2];
                    break;
            }
            if(Math.abs(denominator) < 0.00001) {//ray is parallel to the plane
                if (numerator < 0) return false;
                else;
            }else{
                tHit = numerator / denominator;
                if(denominator > 0){//ray is exiting
                    if(tHit < tOut){//new earlier exit
                        tOut = tHit;
                        outSurface = i;
                    }
                }else{//ray is entering
                    if(tHit > tIn){//new later entry
                        tIn = tHit;
                        inSurface = i;
                    }
                }
            }
            if(tIn >= tOut){//it's a miss, early out
                return false;
            }
        }
        int num = 0; //no positive hits yet
        if(tIn > 0.00001){//is first hit in front of eye?
            Hit hit = new Hit();
            hit.hitTime = tIn;
            hit.hitShape = this;
            hit.isEntering = true;
            hit.surface = inSurface;
            Vector3 point = new Vector3(ray.getOrigin().getVector()[0] + tIn * ray.getDirection().getVector()[0], ray.getOrigin().getVector()[1] + tIn * ray.getDirection().getVector()[1], ray.getOrigin().getVector()[2] + tIn * ray.getDirection().getVector()[2], "point");
            hit.hitPoint = Formulas.applyTransformation(transformMatrix,point,"point");
            Vector3 temp = getNormal(inSurface);
            hit.normal = Formulas.applyTransformation(transformMatrix,temp,"vector");//Formulas.applyTransformation(this.transformMatrix,Vector3.normalise(point, "vector"),"vector"
            //hit.normal = getNormal(inSurface);//Formulas.applyTransformation(this.transformMatrix,Vector3.normalise(point, "vector"),"vector"
            hit.usedRay = ray;
            intersection.hits.add(hit);
            num++; //hit
        }
        if(tOut > 0.00001){
            Hit hit = new Hit();
            hit.hitTime = tOut;
            hit.hitShape = this;
            hit.isEntering = false;
            hit.surface = outSurface;
            Vector3 point = new Vector3(ray.getOrigin().getVector()[0] + tOut * ray.getDirection().getVector()[0], ray.getOrigin().getVector()[1] + tOut * ray.getDirection().getVector()[1], ray.getOrigin().getVector()[2] + tOut * ray.getDirection().getVector()[2], "point");
            hit.hitPoint = point;
            Vector3 temp = getNormal(outSurface);
            hit.normal = Formulas.applyTransformation(transformMatrix,temp,"vector");//Formulas.applyTransformation(this.transformMatrix,Vector3.normalise(point, "vector"),"vector"
            hit.usedRay = ray;
            intersection.hits.add(hit);
            num++; //hit
        }
        return(num>0);
    }

    @Override
    public boolean isPointOnShape(Vector3 point) {
        return false;
    }

    public Vector3 getNormal(int surface){
        Vector3 v = new Vector3(0,0,0,"vector");
        int m = surface/2, n = (surface%2!=0)?-1:1;
        if(m == 0) v = new Vector3(0,n,0,"vector");
        else if(m == 1)  v = new Vector3(n,0,0,"vector");
        else if(m == 2) v = new Vector3(0,0,n,"vector");
        //v = Formulas.applyTransformation(transformMatrix, v, "vector");
        //System.out.println("normal: " + v.toString());
        return v;

    }
}
