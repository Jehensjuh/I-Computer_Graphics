public class REST {
    //class where I will store code that I removed to keep the other code clean but that I don't want to completely get rid of
    //if(usedRay.getRecursion() <= maxReflectionRayBounce){
        /*if(shininess > 0.1){
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
                return new double[]{color.getX(), color.getY(), color.getZ()};
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
            System.out.println("recursion : " + reflected.getRecursion());
        }*/

           /*if(transparency > 0.1){
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
                //correct?
                c1 = PhongMaterials.air().getDc();
                c2 = myobject.getMaterial().getDc();
                usedRay.shapes.add(myobject);
            }
            //add or remove the shape based on entry or exit

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
                return new double[]{color.getX(), color.getY(), color.getZ()};
            }

            double[]tempColor = PhongShading.shading(intersection1.hits.get(0),transmitted, lights, shapes, intersection1);
            Vector3 transmittedColor = new Vector3(tempColor[0], tempColor[1], tempColor[2], "vector");
            color.addVector(transmittedColor);
            //System.out.println("recursion : " + transmitted.getRecursion());
        }*/

}
