package Visual;

import Figures.*;
import MathStuff.Formulas;
import MathStuff.MatrixOperations;
import MathStuff.Transformations;
import MathStuff.Vector3;
import RayTracing.PhongMaterials;

import java.util.ArrayList;

public class Scene {


    public static void createScene(ArrayList<LightSource> lights, ArrayList<Shape> shapes, String sceneName){
        if(sceneName == "scene1"){
            scene1(lights,shapes);
        }else if(sceneName == "shapeScene"){
            shapeScene(lights,shapes);
        }else if(sceneName == "sphereTransformationScene"){
            transformationScene1(lights, shapes);
        }else if(sceneName == "rotationTransformationScene"){
            transformationScene2(lights, shapes);
        }else if(sceneName == "shearTransformationScene"){
            transformationScene3(lights, shapes);
        }else if(sceneName == "reflectionScene1"){
            reflectionScene1(lights, shapes);
        }else if(sceneName == "reflectionScene2"){
            reflectionScene2(lights, shapes);
        }else if(sceneName == "refractionScene1"){
            refractionScene1(lights, shapes);
        }else if(sceneName == "refractionScene2"){
            refractionScene2(lights, shapes);
        }else if(sceneName == "textures1"){
            textureScene1(lights, shapes);
        }else if(sceneName == "textures2"){
            textureScene2(lights, shapes);
        }else if(sceneName == "boolean1"){
            booleanScene1(lights, shapes);
        }else if(sceneName == "boolean2"){
            booleanScene2(lights, shapes);
        }
    }

    private static void scene1(ArrayList<LightSource> lights, ArrayList<Shape> shapes){
        //create scene 1
        LightSource light1 = new LightSource(0,0,10,10);
        lights.add(light1);

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

        Cone cone = new Cone("bottom");
        cone.setMaterial(PhongMaterials.copper());

        Sphere boolSphereA = new Sphere(new Vector3(0,0,0,"vector"));
        boolSphereA.setMaterial(PhongMaterials.ruby());
        Formulas.setBasicTransformation(1,1,1,0,1.2,0,0,boolSphereA);
        Sphere boolSphereB = new Sphere(new Vector3(0,0,0,"vector"));
        boolSphereB.setMaterial(PhongMaterials.ruby());
        Formulas.setBasicTransformation(1,1,1,0,0,0,0,boolSphereB);
        BooleanIntersect lense = new BooleanIntersect(boolSphereB,boolSphereA, "difference");
        lense.setMaterial(PhongMaterials.ruby());

        Cube boolCube1 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        Formulas.setBasicTransformation(1,1,1,0,-1,0,0,boolCube1);
        Cube boolCube2 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        Formulas.setBasicTransformation(1,1,1,0,0,0,0,boolCube2);

        BooleanIntersect cubething = new BooleanIntersect(boolCube1,boolSphereB, "difference");
        cubething.setMaterial(PhongMaterials.cyanRubber());

        BooleanIntersect longcube = new BooleanIntersect(boolCube1,boolCube2,"difference");
        longcube.setMaterial(PhongMaterials.cyanRubber());

        //set transformations
        //Formulas.setBasicTransformation(1,1,1,45,-3,1,-8,cube);//-3 2 -5
        Formulas.setBasicTransformation(1,1,1,0,0,0,0,cube);
        Formulas.setBasicTransformation(1,1,1,0,-3,2,-1,sphere);
        Formulas.setBasicTransformation(1,1,1,0,3,5,-1,sphere2);
        Formulas.setBasicTransformation(5,5,5,90,0,0,0,sq);//omdat 90 gedraaid is y en z translatie omgedraaid
        Formulas.setBasicTransformation(1,1,1,0,2,-2,7,sq2);
        Formulas.setBasicTransformation(1,1,1,0,0,0,0,cylinder);
        Formulas.setBasicTransformation(1,1,1,90,3,0,0,plane);
        Formulas.setBasicTransformation(1,1,1,0,0,0,0,lense);
        Formulas.setBasicTransformation(1,1,1,0,0,0,0,cubething);
        Formulas.setBasicTransformation(1,1,1,90,0,0,0,cone);
        Formulas.setBasicTransformation(1,1,1,0,0,0,0,longcube);

        //set textures
        //boolCube1.setTexture("marble");
        //sphere.setTexture("smooth");
        //sphere2.setTexture("checkerboard");
        //plane.setTexture("checkerboard");
        //cone.setTexture("checkerboard");
        cube.setTexture("trippy");
        boolSphereA.setTexture("wood");

        //add shapes to the scene
        //shapes.add(cylinder);
        //shapes.add(sphere);
        //shapes.add(sphere2);
        shapes.add(cube);
        //shapes.add(sq);
        //shapes.add(sq2);
        //shapes.add(plane);
        //shapes.add(lense);
        //shapes.add(cubething);
        //shapes.add(boolSphereB);
        shapes.add(boolSphereA);
        //shapes.add(boolCube1);
        //shapes.add(cone);
        //shapes.add(longcube);
    }

    private static void shapeScene(ArrayList<LightSource> lights, ArrayList<Shape> shapes){
        LightSource light1 = new LightSource(-2,2,5,10);
        lights.add(light1);

        //initialize shapes
        Sphere sphere = new Sphere(new Vector3(0,0,0,"vector"));
        Cube cube = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        Cylinder cylinder = new Cylinder();
        Plane plane = new Plane();
        Cone cone = new Cone("both");

        //set transformations
        Formulas.setBasicTransformation(1,1,1,0,-6,0,0,sphere);
        Formulas.setBasicTransformation(1,1,1,0,-3,0,0,cube);
        Formulas.setBasicTransformation(1,1,1,90,0,0,0,cylinder);
        Formulas.setBasicTransformation(1,1,1,90,0,-2,0,plane);
        Formulas.setBasicTransformation(1,1,1,90,3,0,0,cone);

        //set materials
        sphere.setMaterial(PhongMaterials.redRubber());
        cube.setMaterial(PhongMaterials.greenRubber());
        cylinder.setMaterial(PhongMaterials.cyanRubber());
        plane.setMaterial(PhongMaterials.yellowRubber());
        cone.setMaterial(PhongMaterials.whiteRubber());

        //set textures

        //add shapes to the scene
        shapes.add(sphere);
        shapes.add(cube);
        shapes.add(cylinder);
        shapes.add(plane);
        shapes.add(cone);
    }

    private static void transformationScene1(ArrayList<LightSource> lights, ArrayList<Shape> shapes){
        LightSource light1 = new LightSource(-2,0,10,10);
        lights.add(light1);

        //initialize shapes
        Sphere sphere1 = new Sphere(new Vector3(0,0,0,"vector"));
        Sphere sphere2 = new Sphere(new Vector3(0,0,0,"vector"));
        Sphere sphere3 = new Sphere(new Vector3(0,0,0,"vector"));

        //initialize transformations
        double[][] transformationSphere1 = MatrixOperations.multiplication(Transformations.scaleMatrix(2,2,2),Transformations.translationMatrix(-6,0,0));
        //double[][] inversetransformationSphere1 = MatrixOperations.multiplication(Transformations.inverseTranslationMatrix(-2,0,0), Transformations.inverseScaleMatrix(2,1,1));
        double[][] inversetransformationSphere1 = MatrixOperations.multiplication(Transformations.inverseScaleMatrix(2,2,2),Transformations.inverseTranslationMatrix(-6,0,0));
        sphere1.setTransform(transformationSphere1);
        sphere1.setInverseTransform(inversetransformationSphere1);

        double[][] transformationSphere2 = MatrixOperations.multiplication(Transformations.scaleMatrix(0.5,1,0.5),Transformations.translationMatrix(-2,0,0));
        double[][] inversetransformationSphere2 = MatrixOperations.multiplication(Transformations.inverseScaleMatrix(0.5,1,0.5),Transformations.inverseTranslationMatrix(-2,0,0));
        sphere2.setTransform(transformationSphere2);
        sphere2.setInverseTransform(inversetransformationSphere2);

        double[][] transformationSphere3 = MatrixOperations.multiplication(Transformations.scaleMatrix(0.5,0.25,0.25),Transformations.translationMatrix(0,0,0));
        double[][] inversetransformationSphere3 = MatrixOperations.multiplication(Transformations.inverseScaleMatrix(0.5,0.25,0.25),Transformations.inverseTranslationMatrix(0,0,0));
        sphere3.setTransform(transformationSphere3);
        sphere3.setInverseTransform(inversetransformationSphere3);

        //set materials
        sphere1.setMaterial(PhongMaterials.whiteRubber());
        sphere2.setMaterial(PhongMaterials.redRubber());
        sphere3.setMaterial(PhongMaterials.greenRubber());
        //set textures

        //add shapes to the scene
        shapes.add(sphere1);
        shapes.add(sphere2);
        shapes.add(sphere3);
    }

    private static void transformationScene2(ArrayList<LightSource> lights, ArrayList<Shape> shapes){
        LightSource light1 = new LightSource(-2,0,10,10);
        lights.add(light1);

        //initialize shapes
        Cube cube1 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        Cube cube2 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        Cube cube3 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));

        //initialize transformations
        double[][] transformationCube1 = MatrixOperations.multiplication(Transformations.xRotationMatrix(Math.toRadians(45)),Transformations.translationMatrix(-6,0,0));
        double[][] inversetransformationCube1 = MatrixOperations.multiplication(Transformations.inversexRotationMatrix(Math.toRadians(45)),Transformations.inverseTranslationMatrix(-6,0,0));
        cube1.setTransform(transformationCube1);
        cube1.setInverseTransform(inversetransformationCube1);

        double[][] transformationCube2 = MatrixOperations.multiplication(Transformations.yRotationMatrix(Math.toRadians(45)),Transformations.translationMatrix(-2,0,0));
        double[][] inversetransformationCube2 = MatrixOperations.multiplication(Transformations.inverseyRotationMatrix(Math.toRadians(45)),Transformations.inverseTranslationMatrix(-2,0,0));
        cube2.setTransform(transformationCube2);
        cube2.setInverseTransform(inversetransformationCube2);

        double[][] transformationCube3 = MatrixOperations.multiplication(Transformations.zRotationMatrix(Math.toRadians(45)),Transformations.translationMatrix(2,0,0));
        double[][] inversetransformationCube3 = MatrixOperations.multiplication(Transformations.inversezRotationMatrix(Math.toRadians(45)),Transformations.inverseTranslationMatrix(2,0,0));
        cube3.setTransform(transformationCube3);
        cube3.setInverseTransform(inversetransformationCube3);

        //set materials
        cube1.setMaterial(PhongMaterials.whiteRubber());
        cube2.setMaterial(PhongMaterials.redRubber());
        cube3.setMaterial(PhongMaterials.greenRubber());

        //set textures

        //add shapes to the scene
        shapes.add(cube1);
        shapes.add(cube2);
        shapes.add(cube3);
    }

    private static void transformationScene3(ArrayList<LightSource> lights, ArrayList<Shape> shapes){
        LightSource light1 = new LightSource(-2,0,10,10);
        lights.add(light1);

        //initialize shapes
        Sphere sphere1 = new Sphere(new Vector3(0,0,0,"vector"));
        Cube cube1 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        Cylinder cylinder1 = new Cylinder();

        //initialize transformations
        double[][] transformationSphere1 = MatrixOperations.multiplication(Transformations.xShearMatrix(1,1),Transformations.translationMatrix(-6,0,0));
        double[][] inversetransformationSphere1 = MatrixOperations.multiplication(Transformations.inversexShearMatrix(1,1),Transformations.inverseTranslationMatrix(-6,0,0));
        sphere1.setTransform(transformationSphere1);
        sphere1.setInverseTransform(inversetransformationSphere1);

        double[][] transformationCube1 = MatrixOperations.multiplication(Transformations.yShearMatrix(1,1),Transformations.translationMatrix(-2,0,0));
        double[][] inversetransformationCube1 = MatrixOperations.multiplication(Transformations.inverseyShearMatrix(1,1),Transformations.inverseTranslationMatrix(-2,0,0));
        cube1.setTransform(transformationCube1);
        cube1.setInverseTransform(inversetransformationCube1);

        double[][] transformationCylinder1 = MatrixOperations.multiplication(Transformations.zShearMatrix(1,1),Transformations.translationMatrix(2,0,0));
        double[][] inversetransformationCylinder1 = MatrixOperations.multiplication(Transformations.inversezShearMatrix(1,1),Transformations.inverseTranslationMatrix(2,0,0));
        cylinder1.setTransform(transformationCylinder1);
        cylinder1.setInverseTransform(inversetransformationCylinder1);

        //set materials
        sphere1.setMaterial(PhongMaterials.whiteRubber());
        cube1.setMaterial(PhongMaterials.redRubber());
        cylinder1.setMaterial(PhongMaterials.greenRubber());

        //set textures

        //add shapes to the scene
        shapes.add(sphere1);
        shapes.add(cube1);
        shapes.add(cylinder1);
    }

    private static void reflectionScene1(ArrayList<LightSource> lights, ArrayList<Shape> shapes){
        LightSource light1 = new LightSource(-6,0,10,10);
        lights.add(light1);

        //initialize shapes
        Sphere sphere1 = new Sphere(new Vector3(0,0,0,"vector"));
        Plane square1 = new Plane();
        Cube cube = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));

        //initialize transformations
        double[][] transformationSquare1 = MatrixOperations.multiplication(MatrixOperations.multiplication(Transformations.scaleMatrix(1,1,1),Transformations.yRotationMatrix(Math.toRadians(-45))),Transformations.translationMatrix(0,0,2));
        double[][] inversetransformationSquare1 = MatrixOperations.multiplication(MatrixOperations.multiplication(Transformations.inverseScaleMatrix(1,1,1),Transformations.inverseyRotationMatrix(Math.toRadians(-45))),Transformations.inverseTranslationMatrix(0,0,2));
        square1.setTransform(transformationSquare1);
        square1.setInverseTransform(inversetransformationSquare1);

        Formulas.setBasicTransformation(1,1,1,0,-4,-2,2,sphere1);
        Formulas.setBasicTransformation(1,1,1,0,-4,2,2,cube);

        //set materials
        sphere1.setMaterial(PhongMaterials.cyanPlastic());
        square1.setMaterial(PhongMaterials.ruby());
        cube.setMaterial(PhongMaterials.gold());

        //set textures

        //add shapes to the scene
        shapes.add(sphere1);
        shapes.add(square1);
        shapes.add(cube);

    }

    private static void reflectionScene2(ArrayList<LightSource> lights, ArrayList<Shape> shapes){
        LightSource light1 = new LightSource(-6,0,10,10);
        lights.add(light1);

        //initialize shapes
        Sphere sphere1 = new Sphere(new Vector3(0,0,0,"vector"));
        Cube cube = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));

        //initialize transformations
        Formulas.setBasicTransformation(1,1,1,0,-4,0,5,sphere1);
        Formulas.setBasicTransformation(1,1,1,0,-3,0,3,cube);

        //set materials
        sphere1.setMaterial(PhongMaterials.cyanPlastic());
        cube.setMaterial(PhongMaterials.gold());

        //set textures

        //add shapes to the scene
        shapes.add(sphere1);
        shapes.add(cube);
    }

    private static void refractionScene1(ArrayList<LightSource> lights, ArrayList<Shape> shapes){
        LightSource light1 = new LightSource(-6,0,10,10);
        lights.add(light1);

        //initialize shapes
        Cylinder cylinder1 = new Cylinder();
        Sphere cylinder2 = new Sphere(new Vector3(0,0,0,"vector"));
        Plane plane = new Plane();

        //initialize transformations
        //Formulas.setBasicTransformation(0.05,0.05,1,90,-2,1,0,cylinder1);
        double[][] temp = MatrixOperations.multiplication(Transformations.xRotationMatrix(Math.toRadians(45)),Transformations.yRotationMatrix(Math.toRadians(-45)));
        double[][] inverseTemp = MatrixOperations.multiplication(Transformations.inversexRotationMatrix(Math.toRadians(45)),Transformations.inverseyRotationMatrix(Math.toRadians(-45)));
        double[][] transformationCylinder1 = MatrixOperations.multiplication(MatrixOperations.multiplication(Transformations.scaleMatrix(0.05,0.05,1),temp),Transformations.translationMatrix(-1.5,1,0));
        double[][] inversetransformationCylinder1 = MatrixOperations.multiplication(MatrixOperations.multiplication(Transformations.inverseScaleMatrix(0.05,0.05,1),inverseTemp),Transformations.inverseTranslationMatrix(-1.5,1,0));
        cylinder1.setTransform(transformationCylinder1);
        cylinder1.setInverseTransform(inversetransformationCylinder1);
        Formulas.setBasicTransformation(1,1,1,0,-2,0,0,cylinder2);
        Formulas.setBasicTransformation(1,1,1,0,0,0,-5,plane);

        //set materials
        cylinder1.setMaterial(PhongMaterials.redRubber());
        cylinder2.setMaterial(PhongMaterials.water());
        plane.setMaterial(PhongMaterials.yellowRubber());

        //set textures

        //add shapes to the scene
        shapes.add(cylinder1);
        shapes.add(cylinder2);
        shapes.add(plane);
    }

    private static void refractionScene2(ArrayList<LightSource> lights, ArrayList<Shape> shapes){
        LightSource light1 = new LightSource(-6,0,10,10);
        lights.add(light1);

        //initialize shapes
        Sphere sphere1 = new Sphere(new Vector3(0,0,0,"vector"));
        Cube cube = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        Plane plane = new Plane();

        //initialize transformations
        Formulas.setBasicTransformation(1,1,1,0,-2,0,8,sphere1);
        Formulas.setBasicTransformation(1,1,1,45,-2,0.5,1,cube);
        Formulas.setBasicTransformation(1,1,1,0,0,0,-5,plane);

        //set materials
        sphere1.setMaterial(PhongMaterials.sapphire());
        cube.setMaterial(PhongMaterials.ruby());
        plane.setMaterial(PhongMaterials.yellowRubber());

        //set textures

        //add shapes to the scene
        shapes.add(sphere1);
        shapes.add(cube);
        //shapes.add(plane);
    }

    private static void textureScene1(ArrayList<LightSource> lights, ArrayList<Shape> shapes){
        LightSource light1 = new LightSource(-6,0,10,10);
        lights.add(light1);

            //initialize shapes
            Cube cube1 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
            Cube cube2 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
            Cube cube3 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));

            Sphere sphere1 = new Sphere(new Vector3(0,0,0,"vector"));
            Sphere sphere2 = new Sphere(new Vector3(0,0,0,"vector"));
            Sphere sphere3 = new Sphere(new Vector3(0,0,0,"vector"));

            //initialize transformations
            Formulas.setBasicTransformation(1,1,1,0,-6,2,0,cube1);
            Formulas.setBasicTransformation(1,1,1,0,-2,2,0,cube2);
            Formulas.setBasicTransformation(1,1,1,0,2,2,0,cube3);
            Formulas.setBasicTransformation(1,1,1,0,-6,-2,0,sphere1);
            Formulas.setBasicTransformation(1,1,1,0,-2,-2,0,sphere2);
            Formulas.setBasicTransformation(1,1,1,0,2,-2,0,sphere3);


            //set materials
            cube1.setMaterial(PhongMaterials.yellowRubber());
            cube2.setMaterial(PhongMaterials.yellowRubber());
            cube3.setMaterial(PhongMaterials.yellowRubber());
            sphere1.setMaterial(PhongMaterials.ruby());
            sphere2.setMaterial(PhongMaterials.ruby());
            sphere3.setMaterial(PhongMaterials.ruby());


            //set textures
            cube1.setTexture("smooth");
            cube2.setTexture("smooth");
            cube3.setTexture("smooth");
            sphere1.setTexture("checkerboard");
            sphere2.setTexture("wood");
            sphere3.setTexture("trippy");

            //add shapes to the scene
            shapes.add(cube1);
            shapes.add(cube2);
            shapes.add(cube3);
            shapes.add(sphere1);
            shapes.add(sphere2);
            shapes.add(sphere3);
    }

    private static void textureScene2(ArrayList<LightSource> lights, ArrayList<Shape> shapes){
        LightSource light1 = new LightSource(-6,0,10,10);
        lights.add(light1);

        //initialize shapes
        Cube cube1 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        Cube cube2 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        Cube cube3 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));

        Sphere sphere1 = new Sphere(new Vector3(0,0,0,"vector"));
        Sphere sphere2 = new Sphere(new Vector3(0,0,0,"vector"));
        Sphere sphere3 = new Sphere(new Vector3(0,0,0,"vector"));

        //initialize transformations
        Formulas.setBasicTransformation(1,1,1,0,-4,2,3,cube1);
        Formulas.setBasicTransformation(1,1,1,0,0,2,3,cube2);
        Formulas.setBasicTransformation(1,1,1,0,2,2,0,cube3);
        Formulas.setBasicTransformation(1,1,1,0,-4,-2,3,sphere1);
        Formulas.setBasicTransformation(1,1,1,0,0,-2,3,sphere2);
        Formulas.setBasicTransformation(1,1,1,0,2,-2,0,sphere3);


        //set materials
        cube1.setMaterial(PhongMaterials.jade());
        cube2.setMaterial(PhongMaterials.obsidian());
        cube3.setMaterial(PhongMaterials.obsidian());
        sphere1.setMaterial(PhongMaterials.copper());
        sphere2.setMaterial(PhongMaterials.silver());
        sphere3.setMaterial(PhongMaterials.ruby());


        //set textures
        cube1.setTexture("smiley");
        cube2.setTexture("wood");
        cube2.getTexture().setAmountOfRings(1);
        cube3.setTexture("trippy");
        sphere1.setTexture("smiley");
        sphere2.setTexture("wood");
        sphere2.getTexture().setAmountOfRings(10);
        sphere3.setTexture("trippy");

        //add shapes to the scene
        shapes.add(cube1);
        shapes.add(cube2);
        //shapes.add(cube3);
        shapes.add(sphere1);
        shapes.add(sphere2);
        //shapes.add(sphere3);
    }

    private static void booleanScene1(ArrayList<LightSource> lights, ArrayList<Shape> shapes){
        LightSource light1 = new LightSource(0,5,10,10);
        lights.add(light1);

        //initialize shapes
        Cube cube1 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        Cube cube2 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        Cube cube3 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));

        Sphere sphere1 = new Sphere(new Vector3(0,0,0,"vector"));
        Sphere sphere2 = new Sphere(new Vector3(0,0,0,"vector"));
        Sphere sphere3 = new Sphere(new Vector3(0,0,0,"vector"));

        BooleanIntersect bool1 = new BooleanIntersect(sphere1,sphere2,"union");
        BooleanIntersect bool2 = new BooleanIntersect(bool1,sphere3,"union");
        BooleanIntersect bool3 = new BooleanIntersect(cube1,sphere1,"intersection");

        //initialize transformations
        Formulas.setBasicTransformation(1,1,1,0,-4,2,2,cube1);
        Formulas.setBasicTransformation(1,1,1,0,0,2,3,cube2);
        Formulas.setBasicTransformation(1,1,1,0,2,2,0,cube3);
        Formulas.setBasicTransformation(0.5,0.5,0.5,0,-3,1,3,sphere1);
        Formulas.setBasicTransformation(1,1,1,0,-2,0,3,sphere2);
        Formulas.setBasicTransformation(0.5,0.5,0.5,0,-1,1,3,sphere3);
        Formulas.setBasicTransformation(1,1,1,0,-2,2,0,bool2);
        Formulas.setBasicTransformation(2,2,2,45,6,2,0,bool3);

        //set materials
        cube1.setMaterial(PhongMaterials.jade());
        cube2.setMaterial(PhongMaterials.obsidian());
        cube3.setMaterial(PhongMaterials.obsidian());
        sphere1.setMaterial(PhongMaterials.copper());
        sphere2.setMaterial(PhongMaterials.silver());
        sphere3.setMaterial(PhongMaterials.ruby());
        bool2.setMaterial(PhongMaterials.emerald());
        bool3.setMaterial(PhongMaterials.yellowRubber());


        //set textures
        bool2.setTexture("wood");
        bool3.setTexture("trippy");

        //add shapes to the scene
        //shapes.add(cube1);
        //shapes.add(cube2);
        //shapes.add(cube3);
        //shapes.add(sphere1);
        //shapes.add(sphere2);
        //shapes.add(sphere3);
        shapes.add(bool2);
        shapes.add(bool3);

    }

    private static void booleanScene2(ArrayList<LightSource> lights, ArrayList<Shape> shapes){
        LightSource light1 = new LightSource(-2,5,10,10);
        lights.add(light1);

        //initialize shapes
        Cube cube1 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        Cube cube2 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));
        Cube cube3 = new Cube(new Vector3(0,0,0,"point"), new Vector3(0,0,0,"vector"));

        Sphere sphere1 = new Sphere(new Vector3(0,0,0,"vector"));
        Sphere sphere2 = new Sphere(new Vector3(0,0,0,"vector"));
        Sphere sphere3 = new Sphere(new Vector3(0,0,0,"vector"));

        Plane plane1 = new Plane();

        BooleanIntersect bool1 = new BooleanIntersect(sphere1,sphere2,"intersection");


        //initialize transformations
        Formulas.setBasicTransformation(1,1,1,0,-4,0,2,cube1);
        Formulas.setBasicTransformation(1,1,1,0,0,0,2,cube2);
        Formulas.setBasicTransformation(1,1,1,0,2,2,0,cube3);
        Formulas.setBasicTransformation(1,1,1,0,-2.5,0,3,sphere1);
        Formulas.setBasicTransformation(1,1,1,0,-1.5,0,3,sphere2);
        Formulas.setBasicTransformation(1,1,1,0,-2,1,3,sphere3);
        Formulas.setBasicTransformation(1,1,1,0,0,0,5,bool1);
        Formulas.setBasicTransformation(1,1,1,0,0,0,-5,plane1);


        //set materials
        cube1.setMaterial(PhongMaterials.jade());
        cube2.setMaterial(PhongMaterials.obsidian());
        cube3.setMaterial(PhongMaterials.obsidian());
        sphere1.setMaterial(PhongMaterials.copper());
        sphere2.setMaterial(PhongMaterials.silver());
        sphere3.setMaterial(PhongMaterials.ruby());
        bool1.setMaterial(PhongMaterials.water());
        plane1.setMaterial(PhongMaterials.redRubber());



        //set textures


        //add shapes to the scene
        shapes.add(cube1);
        shapes.add(cube2);
        //shapes.add(cube3);
        //shapes.add(sphere1);
        //shapes.add(sphere2);
        //shapes.add(sphere3);
        shapes.add(bool1);
        shapes.add(plane1);


    }
}
