package Figures;

import MathStuff.Formulas;
import MathStuff.Vector3;
import RayTracing.Hit;
import RayTracing.Intersection;
import RayTracing.PhongMaterials;
import RayTracing.Ray;

public class Lense extends Boolean{

    public Lense() {
        this.origin = new Vector3(0,0,0,"point"); //a point on the plane
        this.inverseTransformMatrix = null;
        this.transformMatrix = null;
        this.color = new Vector3(50,0,0,"vector");
        this.material = PhongMaterials.brass();
        this.setPriority(6);
    }


    @Override
    public boolean calculateIntersection(Ray rayer, Intersection inter) {
        Ray ray = rayer;

        if (this.inverseTransformMatrix != null) {
            ray = Formulas.applyInverseTransformation(this.inverseTransformMatrix, ray);
        }

        Vector3 rayOrigin = Vector3.subtractVector(ray.getOrigin(), this.origin, "point");
        Vector3 rayDirection = ray.getDirection();

        double A = Vector3.dotProduct(rayDirection, rayDirection);
        double B = Vector3.dotProduct(rayOrigin, rayDirection);
        double C = Vector3.dotProduct(rayOrigin, rayOrigin) - 1;

        double discrim = (B * B) - (A * C);

        if (discrim < 0.0001) {
            // No intersection
            return false;
        } else {
            double discrimSQRT = Math.sqrt(discrim);
            double t1 = (-B - discrimSQRT) / A;
            double t2 = (-B + discrimSQRT) / A;

            int num = 0; // Number of hits

            if (t1 > 0.0001) {
                // First hit
                Hit hit1 = createHit(t1, rayOrigin, rayDirection, true, ray);
                inter.hits.add(hit1);
                num = 1;
            }

            if (t2 > 0.0001) {
                // Second hit
                Hit hit2 = createHit(t2, rayOrigin, rayDirection, false, ray);
                inter.hits.add(hit2);
                num++;
            }

            return num > 0;
        }
    }

    @Override
    public boolean isPointOnShape(Vector3 point) {
        return false;
    }

    private Hit createHit(double t, Vector3 rayOrigin, Vector3 rayDirection, boolean isEntering, Ray ray) {
        Hit hit = new Hit();
        hit.hitTime = t;
        hit.hitShape = this;
        hit.isEntering = isEntering;
        hit.surface = 0;

        Vector3 point = new Vector3(rayOrigin.getVector()[0] + t * rayDirection.getVector()[0],
                rayOrigin.getVector()[1] + t * rayDirection.getVector()[1],
                rayOrigin.getVector()[2] + t * rayDirection.getVector()[2], "point");

        hit.hitPoint = Formulas.applyTransformation(transformMatrix, point, "point");
        hit.normal = point; // Adjust the normal as needed

        hit.usedRay = ray;

        return hit;
    }

}
