package RayTracing;

import Figures.Shape;
import MathStuff.Formulas;
import MathStuff.Transformations;
import MathStuff.Vector3;
import Visual.LightSource;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.max;

public class PhongShading {

    public static double[] shading(Hit hit,Ray usedRay, ArrayList<LightSource> lights, ArrayList<Shape> shapes, Intersection intersection){

        //whenever we enter a shape we need to keep track of this in the ray

        // Default material properties in case hit.hitShape is null
        double defaultAmbient[] = {0, 0, 0};
        double defaultDiffuse[] = {0, 0, 0};
        double defaultSpecular[] = {0, 0, 0};
        double defaultShininess = 0.5;
        double defaultTransparency = 0.5;

        double ambient[] = hit.hitShape != null ? hit.hitShape.getMaterial().getAmbient() : defaultAmbient;
        double diffuse[] = hit.hitShape != null ? hit.hitShape.getMaterial().getDiffuse() : defaultDiffuse;
        double specular[] = hit.hitShape != null ? hit.hitShape.getMaterial().getSpecular() : defaultSpecular;
        double shininess = hit.hitShape != null ? hit.hitShape.getMaterial().getShininess() : defaultShininess;
        double transparency = hit.hitShape != null ? hit.hitShape.getMaterial().getTransparency() : defaultTransparency;


        int maxRayBounce = 40;
        int maxReflectionRayBounce = 20;
        double shinynessThreshold = 0.5;
        double transparencyThreshold = 0.5;
        int recursion = usedRay.getRecursion();

        Vector3 color = new Vector3(0, 0,0,"vector");


        //color = Vector3.addVector(color, hit.hitShape.getColor(), "vector");//emission color

        //usedRay = Formulas.applyInverseTransformation(hit.hitShape.getInverseTransform(), usedRay);
        usedRay.setRecursion(recursion);
        /*Vector3 v = Vector3.normalise(usedRay.getDirection(), "vector");
        v = new Vector3(-v.getVector()[0], -v.getVector()[1], -v.getVector()[2], "vector");
*/
        Vector3 v = new Vector3(-usedRay.getDirection().getVector()[0], -usedRay.getDirection().getVector()[1], -usedRay.getDirection().getVector()[2], "vector");
        Vector3.normalise(v, "vector");
        Vector3 Ia = new Vector3(ambient[0], ambient[1], ambient[2], "vector");
        color.addVector(Ia);


        Shape myobject = hit.hitShape;
        Vector3 n = hit.normal;
        //System.out.println("n before transformation: " + n.toString());
        //n = Formulas.applyTransformation(myobject.getInverseTransform(), n, "vector");
        //System.out.println("n after transformation: " + n.toString());
        /*if(!hit.isEntering){
            n = new Vector3(-n.getVector()[0], -n.getVector()[1], -n.getVector()[2], "vector");
        }*/

        n = Vector3.normalise(n, "vector");

        //for each lightsource
        for(LightSource lightSource:lights){
            if(inShade(hit, lightSource, shapes, intersection)){//if it's not in a shade we continue, else we don't
                continue;
            }
            Vector3 s = Vector3.subtractVector(lightSource.getPosition(), hit.hitPoint, "vector");//distance from hitpoint to lightsource
            s = Vector3.normalise(s, "vector");
            double mDots = Vector3.dotProduct(s, n);//lambert
            double[] texture = myobject.getTexture().getColor(Formulas.applyTransformation(hit.hitShape.getInverseTransform(), hit.hitPoint, "point"));
            //System.out.println("point: " + hit.hitPoint.toString()+ "texture "+texture[0]+ " "+texture[1]+ " "+texture[2]);

            if(mDots > 0.0){
                Vector3 diffuseColor = new Vector3(texture[0]*mDots*diffuse[0]*lightSource.getColor().getVector()[0], texture[1]*mDots*diffuse[1]*lightSource.getColor().getVector()[1], texture[2]*mDots*diffuse[2]*lightSource.getColor().getVector()[2], "vector");
                //komt nog iets van textures
                //color = Vector3.addVector(color, diffuseColor, "vector");
                color.addVector(diffuseColor);

                Vector3 h = Vector3.addVector(v, s, "vector");//halfway vector
                h = Vector3.normalise(h, "vector");
                double hDotn = Vector3.dotProduct(h,n);
                if(hDotn > 0.0){
                    // double phong = max(0.0,Vector3.dotProd(h.getVector(),n.getVector())/(h.getMagnitude()*n.getMagnitude()));//dot product of h and n
                    double phong = Math.pow(hDotn, myobject.getMaterial().getSpecularExponent());
                    Vector3 specColor = new Vector3(phong*specular[0]*lightSource.getColor().getVector()[0], phong*specular[1]*lightSource.getColor().getVector()[1], phong*specular[2]*lightSource.getColor().getVector()[2], "vector");
                    color.addVector(specColor);
                }
            }
        }
        if(usedRay.getRecursion()==maxRayBounce){
            return new double[]{color.getX(), color.getY(), color.getZ()};
        }

        calculateReflection(hit, usedRay, shininess, myobject, color, lights, shapes);
        calculateRefraction(hit, usedRay, transparency, myobject, color, lights, shapes);

        return new double[]{color.getX(), color.getY(), color.getZ()};
    }

    public static boolean inShade(Hit hit, LightSource lights, ArrayList<Shape> shapes, Intersection intersection){
        //we create a ray which has the direction of the lightsource towards the hit point.
        //we then see whether this ray intersects with any of the shapes in the scene
        //if any shape has a hitpoint which is closer to the lightsource than the hitpoint of hit, we return true
        //Vector3 point = Formulas.applyTransformation(hit.hitShape.getTransform(), hit.hitPoint, "point");//get the point in the unit shape
        Ray ray = hit.usedRay;
        double gamma = 0.0001;
        Vector3 point = hit.hitPoint;
        Vector3 feelerOrigin = new Vector3(point.getVector()[0] - gamma*ray.getDirection().getVector()[0], point.getVector()[1] - gamma*ray.getDirection().getVector()[1], point.getVector()[2] - gamma*ray.getDirection().getVector()[2], "point");
        //we create a ray which originates in light and goes to the hitpoint on the unit shape
        Vector3 feelerDir = new Vector3(lights.getPosition().getVector()[0] - point.getVector()[0], lights.getPosition().getVector()[1] - point.getVector()[1], lights.getPosition().getVector()[2] - point.getVector()[2], "vector");
        feelerDir = Vector3.normalise(feelerDir, "vector");
        Ray feeler = new Ray(feelerOrigin, feelerDir);
        Intersection tempInter = new Intersection();
        int i = 0;
        double temp = 0.0;
        int counter = 0;
        for(Shape shape:shapes){
            if(shape.calculateIntersection(feeler, tempInter)){
                counter++;
            }
        }
        for(Hit h:tempInter.hits){
            if(h.hitShape != hit.hitShape){
                if(h.hitTime > hit.hitTime){
                    //System.out.println("h.hitTime: " + h.hitTime + " temp: " + h.hitTime+"amount of hits: " + tempInter.hits.size());
                    return true;
                }
            }
        }
        return false;
    }

    public static Vector3 transmitDirection(Vector3 normal, Vector3 dir, double c1, double c2){
        //calculates the direction of the transmitted ray
        double dc = c2/c1;
        normal = Vector3.normalise(normal, "vector");
        dir = Vector3.normalise(dir, "vector");
        double mdir = Vector3.dotProduct(normal, dir);
        double cosI = Math.sqrt(1-dc*dc*(1-mdir*mdir));
        Vector3 t = new Vector3(dc*dir.getVector()[0] + (dc*mdir-cosI)*normal.getVector()[0], dc*dir.getVector()[1] + (dc*mdir-cosI)*normal.getVector()[1], dc*dir.getVector()[2] + (dc*mdir-cosI)*normal.getVector()[2], "vector");
        return t;
    }

    private static Shape getHighestPriorityShape(ArrayList<Shape> shapes) {
        //finds the shape with the highest priority in the list
        Shape highestPriorityShape = null;
        for (Shape shape : shapes) {
            if (highestPriorityShape == null || shape.getPriority() > highestPriorityShape.getPriority()) {
                highestPriorityShape = shape;
            }
        }
        return highestPriorityShape;
    }

    private static Vector3 calculateReflection(Hit hit, Ray usedRay, double shininess, Shape myobject, Vector3 color, ArrayList<LightSource> lights, ArrayList<Shape> shapes){
        //calculates the reflection component of the color
        if(shininess > 0.1){
            //create new ray
            Ray tempRay = new Ray(usedRay.getOrigin(), usedRay.getDirection());
            //tempRay = Formulas.applyInverseTransformation(myobject.getTransformMatrix(), tempRay);
            Ray reflected = new Ray(hit.hitPoint, new Vector3(0,0,0,"vector"));

            Vector3 normal = hit.normal;
            //System.out.println("normal before transform: " + normal.toString());
            //normal = Formulas.applyTransformation(myobject.getInverseTransform(), normal, "vector");
            //System.out.println("normal after transform: " + normal.toString());
            Vector3.normalise(normal, "vector");


            Vector3 tempDirection = new Vector3(tempRay.getDirection().getVector()[0], tempRay.getDirection().getVector()[1], tempRay.getDirection().getVector()[2], "vector");
            Vector3.normalise(tempDirection, "vector");

            //Vector3 tempDirection = v;
            double temp = Vector3.dotProduct(tempDirection,normal);
            Vector3 direction = new Vector3(tempDirection.getVector()[0] - 2*temp*normal.getVector()[0],tempDirection.getVector()[1] - 2*temp*normal.getVector()[1],tempDirection.getVector()[2] - 2*temp*normal.getVector()[2], "vector");
            //Vector3 direction = new Vector3(2*tempDirection.getVector()[0]*normal.getVector()[0]-tempDirection.getVector()[0],2*tempDirection.getVector()[1]*normal.getVector()[1]-tempDirection.getVector()[1],2*tempDirection.getVector()[2]*normal.getVector()[2]-tempDirection.getVector()[2], "vector");
            //Vector3.normalise(direction, "vector");
            Vector3 origin = new Vector3(hit.hitPoint.getVector()[0], hit.hitPoint.getVector()[1], hit.hitPoint.getVector()[2], "point");
            //origin = Vector3.multiply(origin.getVector(), myobject.getTransform(), "point");
            //Vector3.normalise(origin, "point");
            //direction = Vector3.multiply(direction.getVector(), myobject.getTransform(), "vector");
            reflected.setDirection(direction);
            //origin = Formulas.applyTransformation(myobject.getTransform(), origin, "point");
            reflected.setOrigin(origin);
            reflected.setRecursion(usedRay.getRecursion()+1);

            //calculate the intersections of this new ray with all the shapes
            Intersection intersection1 = new Intersection();
            for(Shape s : shapes){
                if(s != myobject){
                    if(s.calculateIntersection(reflected, intersection1)){
                    }
                }
            }
            if(intersection1.hits.isEmpty()){
                return color;
            }
            double tempor = 1000000000;
            Hit newusableHit = new Hit();
            for(Hit newHit: intersection1.hits){
                if(newHit.hitTime < tempor){//then this hit comes before the previous one
                    tempor = newHit.hitTime;
                    //System.out.println("shape= " + newHit.hitShape.toString());
                    newusableHit = newHit;//we'll use this one
                }
            }
            //calculate the color with the new ray
            double[] tempColor = PhongShading.shading(newusableHit,reflected, lights, shapes, intersection1);
            Vector3 reflectedColor = new Vector3(tempColor[0]*myobject.getMaterial().getShininess(), tempColor[1]*myobject.getMaterial().getShininess(), tempColor[2]*myobject.getMaterial().getShininess(), "vector");
            color.addVector(reflectedColor);
            return color;
        }else{
            return color;
        }
    }

    private static Vector3 calculateRefraction(Hit hit, Ray usedRay, double transparency, Shape myobject, Vector3 color, ArrayList<LightSource> lights, ArrayList<Shape> shapes){
        //calculates the refraction component of the color
        if(transparency > 0.1){
            //get normal and direction to calculate transmission direction
            Vector3 normal = hit.normal;
            Vector3 direction = usedRay.getDirection();
            double c1 = 1;
            double c2 = 1;
            Material newMedium = PhongMaterials.air();

            // if entering B, c1 is shape with highest priority, if B is higher then c2 is B else c1=c2;
            //if exiting B c1 is highest priority in list with B, then remove B and c2 is highest priority in list without B
            //decide the values for c1 and c2, c1 should be the medium of the shape with highest priority
            if(!usedRay.shapes.isEmpty()){
                if(hit.isEntering){
                    Shape highestPriorityShape = getHighestPriorityShape(usedRay.shapes);
                    c1 = highestPriorityShape.getMaterial().getDc();
                    newMedium = highestPriorityShape.getMaterial();
                    if(myobject.getPriority()>highestPriorityShape.getPriority()){
                        c2 = myobject.getMaterial().getDc();
                    }else{
                        c2 = c1;
                    }
                    usedRay.shapes.add(myobject);
                }else{
                    Shape highestPriorityShape = getHighestPriorityShape(usedRay.shapes);
                    c1 = highestPriorityShape.getMaterial().getDc();
                    usedRay.shapes.remove(myobject);
                    //if the list is empty we default back to air
                    if(!usedRay.shapes.isEmpty()) {
                        highestPriorityShape = getHighestPriorityShape(usedRay.shapes);
                        c2 = highestPriorityShape.getMaterial().getDc();
                    }else{
                        c2 = PhongMaterials.air().getDc();
                    }
                }
            }else{
                c1 = PhongMaterials.air().getDc();
                c2 = myobject.getMaterial().getDc();
                usedRay.shapes.add(myobject);
            }
            //calculate transmitDirection
            Vector3 transDir = transmitDirection(normal, direction, c1, c2);
            //origin is the hitpoint on the shape
            Vector3 transOrigin = hit.hitPoint;
            //create a new transmission ray
            Ray transmitted = new Ray(transOrigin, transDir);
            //it's a new ray so recursion goes up
            transmitted.setRecursion(usedRay.getRecursion()+1);
            //the new ray will be in the new medium so it get's that medium
            transmitted.setMedium(newMedium);
            //add the shapes the ray is in to the new ray
            transmitted.shapes = new ArrayList<>(usedRay.shapes);
            //calculate the intersections of this new ray with all the shapes
            Intersection intersection1 = new Intersection();
            for(Shape s : shapes){
                if(s != myobject){
                    if(s.calculateIntersection(transmitted, intersection1)){
                    }
                }
            }
            //if no hits were found it ends here
            if(intersection1.hits.isEmpty()){
                return color;
            }
            //create color
            double[]tempColor = PhongShading.shading(intersection1.hits.get(0),transmitted, lights, shapes, intersection1);
            Vector3 transmittedColor = new Vector3(tempColor[0], tempColor[1], tempColor[2], "vector");
            color.addVector(transmittedColor);
            return color;
        }else{
            return color;
        }
    }

}
