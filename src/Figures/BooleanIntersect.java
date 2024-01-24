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
        if(!shapeA.calculateIntersection(ray, Aintersection)||!shapeB.calculateIntersection(ray, Bintersection)){
            return false;
        }
        CombineIntersections(Aintersection, Bintersection, intersection, this, this.booleanOperation);
        return true;
    }

    private static void CombineIntersections(Intersection A, Intersection B, Intersection output, BooleanIntersect booleanIntersect, String booleanOperation){
        boolean inA = false;
        boolean inB = false;
        boolean intersection = false;
        /*Intersection means that all hits in the output Intersection have to be hits where hit.isEntering is true for both A and B*/
            for(Hit Ahit : A.hits){
                for(Hit Bhit : B.hits){
                    if (Ahit.isEntering) {
                        inA = true;
                    }else{
                        inA = false;
                    }
                    if(Bhit.isEntering){
                        inB = true;
                    }else{
                        inB = false;
                    }
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
