package Figures;

import MathStuff.Vector3;
import RayTracing.Hit;
import RayTracing.Intersection;
import RayTracing.Ray;


public class BooleanIntersect extends Shape{


    /*intersection will calculate the intersections with a compound object based on the intersection boolean operation
    For this the two shapes have to be given as parameters.
    The boolean operation is a string that can be either "union", "intersection" or "difference"
    algorithm: we have figure A and figure B and a ray. We store the intersections of the ray with A in an ordered list based on the hit.hitTime. We do the same for B.

     */
    protected Shape shapeA;
    protected Shape shapeB;

    private String booleanOperation;

    public BooleanIntersect(Shape shapeA, Shape shapeB, String booleanOperation){
        this.shapeA = shapeA;
        this.shapeB = shapeB;
        this.material= shapeA.getMaterial();
        this.inverseTransformMatrix = null;
        this.transformMatrix = null;
        this.booleanOperation = booleanOperation;
        this.setPriority(7);
    }

    public void setBooleanOperation(String booleanOperation){
        this.booleanOperation = booleanOperation;
    }

    public String getBooleanOperation(){
        return this.booleanOperation;
    }
    @Override
    public double calculateNormal() {
        return 0;
    }

    @Override
    public boolean calculateIntersection(Ray ray, RayTracing.Intersection intersection) {
        if(this.inverseTransformMatrix != null){
            //we apply the inverse transformation to the ray
            ray = MathStuff.Formulas.applyInverseTransformation(this.inverseTransformMatrix, ray);
        }
        Intersection Aintersection = new Intersection();
        Intersection Bintersection = new Intersection();
        if(booleanOperation.equals("intersection")) {//where both shapes are present
            if(!shapeA.calculateIntersection(ray, Aintersection)||!shapeB.calculateIntersection(ray, Bintersection)){//if either shape A or shape B miss, it's a miss
                return false;
            }
        }else if(booleanOperation.equals("union")){//where either shapes are present
            if(!shapeA.calculateIntersection(ray, Aintersection)&&!shapeB.calculateIntersection(ray, Bintersection)){//if both shape A and shape B miss, it's a miss
                return false;
            }
        }
        CombineIntersections(Aintersection, Bintersection, intersection, this, this.booleanOperation);
        return true;
    }

    private static void CombineIntersections(Intersection A, Intersection B, Intersection output, BooleanIntersect booleanIntersect, String booleanOperation){
        boolean inA = false;
        boolean inB = false;
        boolean intersection = false;
        int i =0;
        //for intersection: always take the hit with smallest hittime. If one list is bigger than the other, ignore the rest of the bigger list
        /*if(booleanOperation.equals("intersection")){
            while(i < A.hits.size() && i < B.hits.size()){
                Hit Ahit = A.hits.get(i);
                Hit Bhit = B.hits.get(i);
                inA = Ahit.isEntering;
                inB = Bhit.isEntering;
                intersection = inA && inB;
                if(intersection){
                    if(Ahit.hitTime < Bhit.hitTime) {
                        Hit newHit = Ahit;
                        newHit.hitShape = booleanIntersect;
                        output.hits.add(newHit);
                    }else if(Ahit.hitTime > Bhit.hitTime){
                        Hit newHit = Bhit;
                        newHit.hitShape = booleanIntersect;
                        output.hits.add(newHit);
                    }else{
                        Hit newHit = Ahit;
                        newHit.hitShape = booleanIntersect;
                        output.hits.add(newHit);
                    }
                }
                i++;
            }
            //for union: always take the hit with smallest hittime. If one list is bigger than the other, add the rest of the bigger list
        }else if(booleanOperation.equals("union")){
            if(A.hits.isEmpty() && !B.hits.isEmpty()){
                for(Hit Bhit : B.hits){
                    Bhit.hitShape = booleanIntersect;
                    output.hits.add(Bhit);
                }
            }else if(!A.hits.isEmpty() && B.hits.isEmpty()){
                for(Hit Ahit : A.hits){
                    Ahit.hitShape = booleanIntersect;
                    output.hits.add(Ahit);
                }
            }else{
                if(A.hits.size() > B.hits.size()) {
                    while (i < B.hits.size()) {
                        Hit Ahit = A.hits.get(i);
                        Hit Bhit = B.hits.get(i);
                        inA = Ahit.isEntering;
                        inB = Bhit.isEntering;
                        intersection = inA || inB;
                        if (intersection) {
                            if (Ahit.hitTime < Bhit.hitTime) {
                                Hit newHit = Ahit;
                                newHit.hitShape = booleanIntersect;
                                output.hits.add(newHit);
                            } else if (Ahit.hitTime > Bhit.hitTime) {
                                Hit newHit = Bhit;
                                newHit.hitShape = booleanIntersect;
                                output.hits.add(newHit);
                            } else {
                                Hit newHit = Ahit;
                                newHit.hitShape = booleanIntersect;
                                output.hits.add(newHit);
                            }
                        }
                        i++;
                    }
                    while (i < A.hits.size()) {
                        Hit Ahit = A.hits.get(i);
                        Ahit.hitShape = booleanIntersect;
                        output.hits.add(Ahit);
                        i++;
                    }
                }else if(A.hits.size() < B.hits.size()) {
                    while (i < A.hits.size()) {
                        Hit Ahit = A.hits.get(i);
                        Hit Bhit = B.hits.get(i);
                        inA = Ahit.isEntering;
                        inB = Bhit.isEntering;
                        intersection = inA || inB;
                        if (intersection) {
                            if (Ahit.hitTime < Bhit.hitTime) {
                                Hit newHit = Ahit;
                                newHit.hitShape = booleanIntersect;
                                output.hits.add(newHit);
                            } else if (Ahit.hitTime > Bhit.hitTime) {
                                Hit newHit = Bhit;
                                newHit.hitShape = booleanIntersect;
                                output.hits.add(newHit);
                            } else {
                                Hit newHit = Ahit;
                                newHit.hitShape = booleanIntersect;
                                output.hits.add(newHit);
                            }
                        }
                        i++;
                    }
                    while (i < B.hits.size()) {
                        Hit Bhit = B.hits.get(i);
                        Bhit.hitShape = booleanIntersect;
                        output.hits.add(Bhit);
                        i++;
                    }
                }else{
                    while (i < A.hits.size()) {
                        Hit Ahit = A.hits.get(i);
                        Hit Bhit = B.hits.get(i);
                        inA = Ahit.isEntering;
                        inB = Bhit.isEntering;
                        intersection = inA || inB;
                        if (intersection) {
                            if (Ahit.hitTime < Bhit.hitTime) {
                                Hit newHit = Ahit;
                                newHit.hitShape = booleanIntersect;
                                output.hits.add(newHit);
                            } else if (Ahit.hitTime > Bhit.hitTime) {
                                Hit newHit = Bhit;
                                newHit.hitShape = booleanIntersect;
                                output.hits.add(newHit);
                            } else {
                                Hit newHit = Ahit;
                                newHit.hitShape = booleanIntersect;
                                output.hits.add(newHit);
                            }
                        }
                        i++;
                    }
                }
            }

            //for difference: always take the hit with the smallest hittime. If the A list is bigger than the B list, use the rest of the A list. If the B list is bigger than the A list, ignore the rest of the B list
        }else if(booleanOperation.equals("difference")){
            while (i < A.hits.size() && i < B.hits.size()) {
                Hit Ahit = A.hits.get(i);
                Hit Bhit = B.hits.get(i);
                inA = Ahit.isEntering;
                inB = Bhit.isEntering;
                intersection = inA && !inB;
                if (intersection) {
                    if (Ahit.hitTime < Bhit.hitTime) {
                        Hit newHit = Ahit;
                        newHit.hitShape = booleanIntersect;
                        output.hits.add(newHit);
                    } else if (Ahit.hitTime > Bhit.hitTime) {
                        Hit newHit = Bhit;
                        newHit.hitShape = booleanIntersect;
                        output.hits.add(newHit);
                    } else {
                        // Handle equal hit times for "difference"
                        Hit newHit = Ahit;
                        newHit.hitShape = booleanIntersect;
                        output.hits.add(newHit);
                    }
                }
                i++;
            }

            // Add remaining hits from shapeA, if any
            while (i < A.hits.size()) {
                Hit Ahit = A.hits.get(i);
                Ahit.hitShape = booleanIntersect;
                output.hits.add(Ahit);
                i++;
            }
        }*/

            for(Hit Ahit : A.hits){
                for(Hit Bhit : B.hits){

                    inA = Ahit.isEntering;
                    inB = Bhit.isEntering;

                    if(booleanOperation.equals("intersection")){
                        intersection = inA && inB;
                    }else if(booleanOperation.equals("union")){
                        intersection = inA || inB;
                    }else if(booleanOperation.equals("difference")){
                        intersection = inA && !inB;
                    }

                    if(intersection){
                        if(Ahit.hitTime < Bhit.hitTime){
                            Hit newHit = Ahit;
                            newHit.hitShape = booleanIntersect;
                            output.hits.add(newHit);
                        }else if(Ahit.hitTime > Bhit.hitTime){
                            Hit newHit = Bhit;
                            newHit.hitShape = booleanIntersect;
                            output.hits.add(newHit);
                        }else{
                            Hit newHit = Ahit;
                            newHit.hitShape = booleanIntersect;
                            output.hits.add(newHit);
                        }
                    }
                }
            }
    }

    @Override
    public boolean isPointOnShape(Vector3 point) {
        return false;
    }
}
