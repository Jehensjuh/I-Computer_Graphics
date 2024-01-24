package RayTracing;

import Figures.Shape;
import MathStuff.Vector3;

import java.util.ArrayList;

public class Ray {
    private Vector3 origin;//a point where the ray starts
    private Vector3 direction;//a vector that points in the direction of the ray
    private Vector3 point;//a point on the ray

    private Material medium;

    public ArrayList<Shape> shapes;

    private int recursion;

    public Ray(Vector3 origin, Vector3 direction) {
        this.origin = origin;
        this.direction = direction;
        this.medium = PhongMaterials.air();
        this.shapes = new ArrayList<>();
    }

    public Material getMedium() {
    	return this.medium;
    }

    public void setMedium(Material medium) {
    	this.medium = medium;
    }

    public void setOrigin(Vector3 origin) {
        this.origin = origin;
    }
    public void setDirection(Vector3 direction) {
        this.direction = direction;
    }

    public Vector3 getOrigin() {
        return this.origin;
    }
    public Vector3 getDirection() {
        return this.direction;
    }

    public Vector3 getPoint() {
        return this.point;
    }

    public void setRecursion(int recursion) {
    	this.recursion = recursion;
    }

    public int getRecursion() {
    	return this.recursion;
    }
}
