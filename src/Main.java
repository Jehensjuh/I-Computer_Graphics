import Figures.*;
import Figures.Shape;
import MathStuff.Formulas;
import MathStuff.MatrixOperations;
import MathStuff.Transformations;
import MathStuff.Vector3;
import RayTracing.*;
import Visual.Eye;
import Visual.LightSource;
import Visual.Screen;

import java.awt.*;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        //initialize the screen
        Screen screen = new Screen();
        //Eye eye = new Eye(0, 0,5,0,0,screen.getN());//-1 2 5
        //initialize the eye
        Eye eye = new Eye(0,0,5,0,0,screen.getN());
        screen.getFrame().addKeyListener(eye);

        //initialize lists of shapes and lights
        ArrayList<LightSource> lights = new ArrayList<>();
        ArrayList<Shape> shapes = new ArrayList<>();
        LightSource light1 = new LightSource(-2,2,11,10);
        //LightSource light2 = new LightSource(2, 4, 4, 20);
        lights.add(light1);
        //lights.add(light2);

        //initialize shapes
        Sphere sphere = new Sphere(new Vector3(0,0,0,"vector"));
        sphere.setMaterial(PhongMaterials.sapphire());

        Sphere sphere2 = new Sphere(new Vector3(0,0,0,"vector"));
        sphere2.setMaterial(PhongMaterials.jade());

        Square sq = new Square();
        sq.setMaterial(PhongMaterials.ruby());

        Square sq2 = new Square();
        sq2.setMaterial(PhongMaterials.ruby());

        Cube cube = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        cube.setMaterial(PhongMaterials.gold());

        Cylinder cylinder = new Cylinder();
        cylinder.setMaterial(PhongMaterials.ruby());

        Plane plane = new Plane();
        plane.setMaterial(PhongMaterials.copper());

        Sphere boolSphereA = new Sphere(new Vector3(0,0,0,"vector"));
        boolSphereA.setMaterial(PhongMaterials.ruby());
        Formulas.setBasicTransformation(1,1,1,0,-1,0,0,boolSphereA);
        Sphere boolSphereB = new Sphere(new Vector3(0,0,0,"vector"));
        boolSphereB.setMaterial(PhongMaterials.ruby());
        Formulas.setBasicTransformation(1,1,1,0,0,0,0,boolSphereB);
        BooleanIntersect lense = new BooleanIntersect(boolSphereA,boolSphereB, "intersection");
        lense.setMaterial(PhongMaterials.ruby());

        Cube boolCube1 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        Formulas.setBasicTransformation(1,1,1,0,-1,0,0,boolCube1);
        Cube boolCube2 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        Formulas.setBasicTransformation(1,1,1,0,0,0,0,boolCube2);

        BooleanIntersect cubething = new BooleanIntersect(boolCube1,boolSphereB, "union");
        cubething.setMaterial(PhongMaterials.cyanRubber());



//first scale then rotate then translate
        double[][] tempTransfer = Transformations.inverseTranslationMatrix(0,0,-5);
        double[][] tempTransfer2 = Transformations.translationMatrix(0,0,-5);

        double[][] sphereTransfer1 = MatrixOperations.multiplication(Transformations.inverseTranslationMatrix(1,3,3),Transformations.inverseScaleMatrix(0.25,0.25,0.25));
        double[][] sphereTransfer2 = MatrixOperations.multiplication(Transformations.scaleMatrix(0.25,0.25,0.25),Transformations.translationMatrix(1,3,3));

        //double[][] cubeTransfer1 = MatrixOperations.multiplication(MatrixOperations.multiplication(Transformations.inverseTranslationMatrix(-1,0,-5),Transformations.inversexRotationMatrix(Math.toRadians(45))),Transformations.inverseScaleMatrix(1,1,1));
        //double[][] cubeTransfer2 = MatrixOperations.multiplication(MatrixOperations.multiplication(Transformations.scaleMatrix(1,1,1),Transformations.xRotationMatrix(Math.toRadians(45))),Transformations.translationMatrix(-1,0,-5));

        double[][] sqTransform = MatrixOperations.multiplication(MatrixOperations.multiplication(Transformations.inverseTranslationMatrix(0,0,0),Transformations.inversexRotationMatrix(Math.toRadians(90))),Transformations.inverseScaleMatrix(1,1,1));
        double[][] sqTransform2 = MatrixOperations.multiplication(MatrixOperations.multiplication(Transformations.scaleMatrix(1,1,1),Transformations.xRotationMatrix(Math.toRadians(90))),Transformations.translationMatrix(0,0,0));

        double[][] sq2Transform = MatrixOperations.multiplication(MatrixOperations.multiplication(Transformations.inverseTranslationMatrix(0,1,-5),Transformations.inversexRotationMatrix(Math.toRadians(0))),Transformations.inverseScaleMatrix(1,1,1));
        double[][] sq2Transform2 = MatrixOperations.multiplication(MatrixOperations.multiplication(Transformations.scaleMatrix(1,1,1),Transformations.xRotationMatrix(Math.toRadians(0))),Transformations.translationMatrix(0,0,-5));

        double[][] cylinderTransform = MatrixOperations.multiplication(MatrixOperations.multiplication(Transformations.inverseTranslationMatrix(0,0,0),Transformations.inversexRotationMatrix(Math.toRadians(45))),Transformations.inverseScaleMatrix(1,1,1));
        double[][] cylinderTransform2 = MatrixOperations.multiplication(MatrixOperations.multiplication(Transformations.scaleMatrix(1,1,1),Transformations.xRotationMatrix(Math.toRadians(45))),Transformations.translationMatrix(0,0,0));

      /*  sphere.setInverseTransform(tempTransfer);
        sphere.setTransform(tempTransfer2);

        sphere2.setInverseTransform(sphereTransfer1);
        sphere2.setTransform(sphereTransfer2);

        sq.setInverseTransform(sqTransform);
        sq.setTransform(sqTransform2);

        sq2.setInverseTransform(sq2Transform);
        sq2.setTransform(sq2Transform2);

        //cube.setInverseTransform(cubeTransfer1);
        //cube.setTransform(cubeTransfer2);
        Formulas.setBasicTransformation(1,1,1,45,-3,0,-5,cube);
        //print out the transformation matrix


        cylinder.setInverseTransform(cylinderTransform);
        cylinder.setTransform(cylinderTransform2);*/

        //set transformations
       //Formulas.setBasicTransformation(1,1,1,45,-3,1,-8,cube);//-3 2 -5
        Formulas.setBasicTransformation(1,1,1,0,-3,2,-5,cube);
        Formulas.setBasicTransformation(1,1,1,0,-3,2,-1,sphere);
        Formulas.setBasicTransformation(1,1,1,0,3,2,-1,sphere2);
        Formulas.setBasicTransformation(1,1,1,90,3,-1,0,sq);//omdat 90 gedraaid is y en z translatie omgedraaid
        Formulas.setBasicTransformation(1,1,1,0,2,-2,7,sq2);
        Formulas.setBasicTransformation(1,1,1,0,0,0,0,cylinder);
        Formulas.setBasicTransformation(1,1,1,0,0,0,0,plane);
        Formulas.setBasicTransformation(1,1,1,0,0,0,0,lense);
        Formulas.setBasicTransformation(1,1,1,0,0,0,0,cubething);

        //add shapes to the scene
        //shapes.add(cylinder);
        //shapes.add(sphere);
        //shapes.add(sphere2);
        //shapes.add(cube);
        //shapes.add(sq);
        //shapes.add(sq2);
        //shapes.add(plane);
        shapes.add(lense);
        //shapes.add(cubething);
        //shapes.add(boolSphereB);
        //shapes.add(boolCube1);

        while(true){
            try {
                double xValueMax = -1000, xValueMin = 1000, yValueMax = -1000, yValueMin = 1000, zValueMax = -1000, zValueMin = 1000;
                //take a look at all pixels
                for(int i = 0; i < screen.getCols(); i++){//0 to 2W
                    for(int j = 0; j< screen.getRows();j++){
                        Intersection inter = new Intersection();

                        int x = -screen.getWidth()+(i*2*screen.getWidth())/screen.getCols();
                        int y = screen.getHeight()-(j*2*screen.getHeight())/screen.getRows();
                        //screen.paint(i,j,Color.BLUE);

                        //calculate the direction of the ray according to the textbook
                        double Uc = Formulas.getUc(i,screen.getWidth(),screen.getCols());
                        double Vr = Formulas.getVr(j,screen.getHeight(),screen.getRows());
                        eye.setN(screen.getN());
                        eye.setUc(Uc);
                        eye.setVc(Vr);
                        eye.transform();
                        //eye.slide("forward");
                        //eye.pitch(Math.toRadians(0));
                        //eye.yaw(Math.toRadians(0));
                        //eye.roll(Math.toRadians(90));
                        Vector3 dir = eye.getDirection();
                        Vector3 pos = eye.getPosition();
                        Ray ray = new Ray(pos,dir);
                        ray.setRecursion(0);
                        if(!shapes.isEmpty()){
                            for(Shape s : shapes){
                                if(s.calculateIntersection(ray, inter)){
                                    /*if(ray.getDirection().getVector()[0]>xValueMax) xValueMax = ray.getDirection().getVector()[0];
                                    if(ray.getDirection().getVector()[0]<xValueMin) xValueMin = ray.getDirection().getVector()[0];
                                    if(ray.getDirection().getVector()[1]>yValueMax) yValueMax = ray.getDirection().getVector()[1];
                                    if(ray.getDirection().getVector()[1]<yValueMin) yValueMin = ray.getDirection().getVector()[1];
                                    if(ray.getDirection().getVector()[2]>zValueMax) zValueMax = ray.getDirection().getVector()[2];
                                    if(ray.getDirection().getVector()[2]<zValueMin) zValueMin = ray.getDirection().getVector()[2];*/
                                }
                            }
                        }
                        double temp = 1000000000;
                        Hit usableHit = new Hit();
                        if(inter.hits.isEmpty()){
                            screen.paint(i,j,Color.BLACK);
                            continue;
                        }
                        for(Hit hit: inter.hits){
                            if(hit.hitTime < temp){//then this hit comes before the previous one
                                temp = hit.hitTime;
                                usableHit = hit;//we'll use this one
                            }
                        }
                        Ray usedRay = usableHit.usedRay;
                        double[] color = PhongShading.shading(usableHit,usedRay, lights, shapes, inter);

                        float[] tempcol = new float[]{(float)color[0],(float)color[1],(float)color[2]};
                /*if(tempcol[1]>1 || tempcol[1]>1 ||tempcol[2]>1){
                    for(int c = 0; c<tempcol.length; c++){
                        tempcol[c] = tempcol[c]/255;
                    }
                }*/
                        for(int c = 0; c < tempcol.length; c++){
                            tempcol[c] = Math.abs(tempcol[c]);
                            tempcol[c] = Math.min(tempcol[c], 1);
                        }
                        Color createdColor = new Color(tempcol[0],tempcol[1],tempcol[2]);
                        screen.paint(i,j,createdColor);
                    }
                }
                /*System.out.println("xValueMax: " + xValueMax+" and xValueMin: "+xValueMin);
                System.out.println("yValueMax: " + yValueMax+" and yValueMin: "+yValueMin);
                System.out.println("zValueMax: " + zValueMax+" and zValueMin: "+zValueMin);
                break;*/
                //break;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}