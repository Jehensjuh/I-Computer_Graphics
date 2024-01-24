package RayTracing;

import Figures.Shape;
import MathStuff.Point;
import MathStuff.Vector3;

public class Hit {
    public double hitTime;
    public Shape hitShape;
    public boolean isEntering;
    public int surface;
    public Vector3 hitPoint;
    public Vector3 normal;
    public Ray usedRay;

    public Hit(){
        this.hitTime = Double.POSITIVE_INFINITY;
        this.hitShape = null;
        this.isEntering = false;
        this.surface = 0;
        this.hitPoint = new Vector3(0,0,0,"point");
        this.normal = new Vector3(0,0,0,"vector");
        this.usedRay = null;

    }
}
