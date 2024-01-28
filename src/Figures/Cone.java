package Figures;

import MathStuff.Vector3;
import RayTracing.Hit;
import RayTracing.Intersection;
import RayTracing.PhongMaterials;
import RayTracing.Ray;
import MathStuff.Formulas;

public class Cone extends Shape{
String type;
    public Cone(String type){
        this.material = PhongMaterials.brass();
        this.inverseTransformMatrix = null;
        this.transformMatrix = null;
        this.setPriority(4);
        this.type = type;
    }
    @Override
    public double calculateNormal() {
        return 0;
    }

    public boolean calculateIntersection(Ray ray, Intersection intersection) {
        // Apply inverse transformation to the ray if available
        if (this.inverseTransformMatrix != null) {
            ray = Formulas.applyInverseTransformation(this.inverseTransformMatrix, ray);
        }

        Vector3 rayOrigin = ray.getOrigin();
        Vector3 rayDirection = ray.getDirection();

        // Quadratic coefficients for the cone equation
        // a = dx^2 + dy^2 - dz^2
        double a = rayDirection.getVector()[0] * rayDirection.getVector()[0]
                + rayDirection.getVector()[1] * rayDirection.getVector()[1]
                - rayDirection.getVector()[2] * rayDirection.getVector()[2];
        // b = 2 * (ox * dx + oy * dy - oz * dz)
        double b = 2 * (rayOrigin.getVector()[0] * rayDirection.getVector()[0]
                + rayOrigin.getVector()[1] * rayDirection.getVector()[1]
                - rayOrigin.getVector()[2] * rayDirection.getVector()[2]);
        // c = ox^2 + oy^2 - oz^2
        double c = rayOrigin.getVector()[0] * rayOrigin.getVector()[0]
                + rayOrigin.getVector()[1] * rayOrigin.getVector()[1]
                - rayOrigin.getVector()[2] * rayOrigin.getVector()[2];

        // Discriminant for quadratic formula
        double discrim = b * b - 4 * a * c;

        // Check for no real roots (no intersection)
        if (discrim < 0.0001) {
            return false;
        } else {
            // Calculate roots using quadratic formula
            double discrimSqrt = Math.sqrt(discrim);
            double t1 = (-b - discrimSqrt) / (2 * a);
            double t2 = (-b + discrimSqrt) / (2 * a);

            if(this.type == "bottom"){
                // Check if the intersection is within a valid range
                if (t1 > 0.0001 && Math.abs(rayOrigin.getVector()[2] + t1 * rayDirection.getVector()[2]) <= 1 && rayOrigin.getVector()[2] + t1 * rayDirection.getVector()[2] > 0) {
                    // Create hit information for t1
                    Hit hit = new Hit();
                    hit.hitShape = this;
                    hit.hitTime = t1;
                    hit.isEntering = true;
                    hit.surface = 0;
                    Vector3 point = new Vector3(rayOrigin.getVector()[0] + t1 * rayDirection.getVector()[0],
                            rayOrigin.getVector()[1] + t1 * rayDirection.getVector()[1],
                            rayOrigin.getVector()[2] + t1 * rayDirection.getVector()[2], "point");
                    hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
                    Vector3 temp = new Vector3(point.getVector()[0], point.getVector()[1], 0, "point");
                    hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
                    hit.usedRay = ray;
                    intersection.hits.add(hit);
                }
                if (t2 > 0.0001 && Math.abs(rayOrigin.getVector()[2] + t2 * rayDirection.getVector()[2]) <= 1&& rayOrigin.getVector()[2] + t1 * rayDirection.getVector()[2] > 0) {
                    // Create hit information for t2
                    Hit hit = new Hit();
                    hit.hitShape = this;
                    hit.hitTime = t2;
                    hit.isEntering = false;
                    hit.surface = 0;
                    Vector3 point = new Vector3(rayOrigin.getVector()[0] + t2 * rayDirection.getVector()[0],
                            rayOrigin.getVector()[1] + t2 * rayDirection.getVector()[1],
                            rayOrigin.getVector()[2] + t2 * rayDirection.getVector()[2], "point");
                    hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
                    Vector3 temp = new Vector3(point.getVector()[0], point.getVector()[1], 0, "point");
                    hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
                    hit.usedRay = ray;
                    intersection.hits.add(hit);
                }
            }else if(this.type == "top"){
                // Check if the intersection is within a valid range
                if (t1 > 0.0001 && Math.abs(rayOrigin.getVector()[2] + t1 * rayDirection.getVector()[2]) <= 1 && rayOrigin.getVector()[2] + t1 * rayDirection.getVector()[2] < 0) {
                    // Create hit information for t1
                    Hit hit = new Hit();
                    hit.hitShape = this;
                    hit.hitTime = t1;
                    hit.isEntering = true;
                    hit.surface = 0;
                    Vector3 point = new Vector3(rayOrigin.getVector()[0] + t1 * rayDirection.getVector()[0],
                            rayOrigin.getVector()[1] + t1 * rayDirection.getVector()[1],
                            rayOrigin.getVector()[2] + t1 * rayDirection.getVector()[2], "point");
                    hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
                    Vector3 temp = new Vector3(point.getVector()[0], point.getVector()[1], 0, "point");
                    hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
                    hit.usedRay = ray;
                    intersection.hits.add(hit);
                }
                if (t2 > 0.0001 && Math.abs(rayOrigin.getVector()[2] + t2 * rayDirection.getVector()[2]) <= 1&& rayOrigin.getVector()[2] + t1 * rayDirection.getVector()[2] < 0) {
                    // Create hit information for t2
                    Hit hit = new Hit();
                    hit.hitShape = this;
                    hit.hitTime = t2;
                    hit.isEntering = false;
                    hit.surface = 0;
                    Vector3 point = new Vector3(rayOrigin.getVector()[0] + t2 * rayDirection.getVector()[0],
                            rayOrigin.getVector()[1] + t2 * rayDirection.getVector()[1],
                            rayOrigin.getVector()[2] + t2 * rayDirection.getVector()[2], "point");
                    hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
                    Vector3 temp = new Vector3(point.getVector()[0], point.getVector()[1], 0, "point");
                    hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
                    hit.usedRay = ray;
                    intersection.hits.add(hit);
                }
            }else{
                // Check if the intersection is within a valid range
                if (t1 > 0.0001 && Math.abs(rayOrigin.getVector()[2] + t1 * rayDirection.getVector()[2]) <= 1) {
                    // Create hit information for t1
                    Hit hit = new Hit();
                    hit.hitShape = this;
                    hit.hitTime = t1;
                    hit.isEntering = true;
                    hit.surface = 0;
                    Vector3 point = new Vector3(rayOrigin.getVector()[0] + t1 * rayDirection.getVector()[0],
                            rayOrigin.getVector()[1] + t1 * rayDirection.getVector()[1],
                            rayOrigin.getVector()[2] + t1 * rayDirection.getVector()[2], "point");
                    hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
                    Vector3 temp = new Vector3(point.getVector()[0], point.getVector()[1], 0, "point");
                    hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
                    hit.usedRay = ray;
                    intersection.hits.add(hit);
                }
                if (t2 > 0.0001 && Math.abs(rayOrigin.getVector()[2] + t2 * rayDirection.getVector()[2]) <= 1) {
                    // Create hit information for t2
                    Hit hit = new Hit();
                    hit.hitShape = this;
                    hit.hitTime = t2;
                    hit.isEntering = false;
                    hit.surface = 0;
                    Vector3 point = new Vector3(rayOrigin.getVector()[0] + t2 * rayDirection.getVector()[0],
                            rayOrigin.getVector()[1] + t2 * rayDirection.getVector()[1],
                            rayOrigin.getVector()[2] + t2 * rayDirection.getVector()[2], "point");
                    hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
                    Vector3 temp = new Vector3(point.getVector()[0], point.getVector()[1], 0, "point");
                    hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
                    hit.usedRay = ray;
                    intersection.hits.add(hit);
                }
            }
        }
        // Check for intersection with the cone caps
        checkCapsIntersectionCone(intersection, ray);

        // Return true if any intersections are found
        return !intersection.hits.isEmpty();
    }

    @Override
    public boolean isPointOnShape(Vector3 point) {
        return false;
    }

    private void checkCapsIntersectionCone(Intersection inter, Ray ray) {
        Vector3 rayOrigin = ray.getOrigin();
        Vector3 rayDirection = ray.getDirection();
        if(this.type == "top"){
            double tBottom = (-rayOrigin.getVector()[2] - 1) / rayDirection.getVector()[2];
            if (tBottom > 0.0001) {
                double x = rayOrigin.getVector()[0] + tBottom * rayDirection.getVector()[0];
                double y = rayOrigin.getVector()[1] + tBottom * rayDirection.getVector()[1];
                if (x * x + y * y <= 1) {
                    // Create hit information for the bottom cap
                    Hit hit = new Hit();
                    hit.hitShape = this;
                    hit.hitTime = tBottom;
                    hit.isEntering = true;
                    hit.surface = 1;
                    Vector3 point = new Vector3(rayOrigin.getVector()[0] + tBottom * rayDirection.getVector()[0],
                            rayOrigin.getVector()[1] + tBottom * rayDirection.getVector()[1],
                            rayOrigin.getVector()[2] + tBottom * rayDirection.getVector()[2], "point");
                    hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
                    Vector3 temp = new Vector3(0, 0, -1, "point");
                    hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
                    hit.usedRay = ray;
                    inter.hits.add(hit);
                }
            }
        }else if(this.type == "bottom"){
            // Intersection with the top cap
            double tTop = (1 - rayOrigin.getVector()[2]) / rayDirection.getVector()[2];
            if (tTop > 0.0001) {
                double x = rayOrigin.getVector()[0] + tTop * rayDirection.getVector()[0];
                double y = rayOrigin.getVector()[1] + tTop * rayDirection.getVector()[1];
                if (x * x + y * y <= 1) {
                    // Create hit information for the top cap
                    Hit hit = new Hit();
                    hit.hitShape = this;
                    hit.hitTime = tTop;
                    hit.isEntering = false;
                    hit.surface = 1;
                    Vector3 point = new Vector3(rayOrigin.getVector()[0] + tTop * rayDirection.getVector()[0],
                            rayOrigin.getVector()[1] + tTop * rayDirection.getVector()[1],
                            rayOrigin.getVector()[2] + tTop * rayDirection.getVector()[2], "point");
                    hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
                    Vector3 temp = new Vector3(0, 0, 1, "point");
                    hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
                    hit.usedRay = ray;
                    inter.hits.add(hit);
                }
            }
        }else{
            double tBottom = (-rayOrigin.getVector()[2] - 1) / rayDirection.getVector()[2];
            if (tBottom > 0.0001) {
                double x = rayOrigin.getVector()[0] + tBottom * rayDirection.getVector()[0];
                double y = rayOrigin.getVector()[1] + tBottom * rayDirection.getVector()[1];
                if (x * x + y * y <= 1) {
                    // Create hit information for the bottom cap
                    Hit hit = new Hit();
                    hit.hitShape = this;
                    hit.hitTime = tBottom;
                    hit.isEntering = true;
                    hit.surface = 1;
                    Vector3 point = new Vector3(rayOrigin.getVector()[0] + tBottom * rayDirection.getVector()[0],
                            rayOrigin.getVector()[1] + tBottom * rayDirection.getVector()[1],
                            rayOrigin.getVector()[2] + tBottom * rayDirection.getVector()[2], "point");
                    hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
                    Vector3 temp = new Vector3(0, 0, -1, "point");
                    hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
                    hit.usedRay = ray;
                    inter.hits.add(hit);
                }
            }
            // Intersection with the top cap
            double tTop = (1 - rayOrigin.getVector()[2]) / rayDirection.getVector()[2];
            if (tTop > 0.0001) {
                double x = rayOrigin.getVector()[0] + tTop * rayDirection.getVector()[0];
                double y = rayOrigin.getVector()[1] + tTop * rayDirection.getVector()[1];
                if (x * x + y * y <= 1) {
                    // Create hit information for the top cap
                    Hit hit = new Hit();
                    hit.hitShape = this;
                    hit.hitTime = tTop;
                    hit.isEntering = false;
                    hit.surface = 1;
                    Vector3 point = new Vector3(rayOrigin.getVector()[0] + tTop * rayDirection.getVector()[0],
                            rayOrigin.getVector()[1] + tTop * rayDirection.getVector()[1],
                            rayOrigin.getVector()[2] + tTop * rayDirection.getVector()[2], "point");
                    hit.hitPoint = Formulas.applyTransformation(this.transformMatrix, point, "point");
                    Vector3 temp = new Vector3(0, 0, 1, "point");
                    hit.normal = Formulas.applyTransformation(this.transformMatrix, temp, "vector");
                    hit.usedRay = ray;
                    inter.hits.add(hit);
                }
            }
        }
    }
}
