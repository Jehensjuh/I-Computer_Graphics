package Figures;

import MathStuff.Vector3;
import RayTracing.Intersection;
import RayTracing.Material;
import RayTracing.Ray;

import java.awt.*;

public abstract class Shape {
    public Vector3 normal;//a vector

    protected double[][] transformMatrix;
    protected double[][] inverseTransformMatrix;

    protected Vector3 color;

    protected Material material;

    private int priority;


    public Vector3 origin;//a point
    public abstract double calculateNormal();
    public abstract boolean calculateIntersection(Ray ray, Intersection intersection);

    public void setTransform(double[][] transformMatrix){
        this.transformMatrix = transformMatrix;
    }

    public void setInverseTransform(double[][] inverseTransformMatrix){
        this.inverseTransformMatrix = inverseTransformMatrix;
    }

    public int getPriority() {
    	return this.priority;
    }
    public void setPriority(int priority) {
    	this.priority = priority;
    }

    public double[][] getTransform(){
        return transformMatrix;
    }

    public double[][] getInverseTransform(){
        return inverseTransformMatrix;
    }

    public Vector3 getColor(){
        return color;
    }



    public void setColor(int r, int g, int b){
        this.color = new Vector3(r,g,b,"vector");
    }

    public Material getMaterial(){
        return material;
    }

    public void setMaterial(Material material){
        this.material = material;
    }
    public double[][] getTransformMatrix(){
        return transformMatrix;
    }
    public abstract boolean isPointOnShape(Vector3 point);
}
