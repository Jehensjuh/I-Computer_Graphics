import Figures.*;
import Figures.Shape;
import MathStuff.Formulas;
import MathStuff.MatrixOperations;
import MathStuff.Transformations;
import MathStuff.Vector3;
import RayTracing.*;
import Visual.Eye;
import Visual.LightSource;
import Visual.Scene;
import Visual.Screen;

import java.awt.*;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        //initialize the screen
        Screen screen = new Screen();
        //Eye eye = new Eye(0, 0,5,0,0,screen.getN());//-1 2 5
        //initialize the eye
        Eye eye = new Eye(-2,0,10,0,0,screen.getN());
        screen.getFrame().addKeyListener(eye);

        //initialize lists of shapes and lights
        ArrayList<LightSource> lights = new ArrayList<>();
        ArrayList<Shape> shapes = new ArrayList<>();

        //first scale then rotate then translate
        Scene.createScene(lights,shapes,"boolean2");

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
                        Vector3 dir = eye.getDirection();
                        Vector3 pos = eye.getPosition();
                        Ray ray = new Ray(pos,dir);
                        ray.setRecursion(0);
                        if(!shapes.isEmpty()){
                            for(Shape s : shapes){
                                if(s.calculateIntersection(ray, inter)){
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
                        for(int c = 0; c < tempcol.length; c++){
                            tempcol[c] = Math.abs(tempcol[c]);
                            tempcol[c] = Math.min(tempcol[c], 1);
                        }
                        Color createdColor = new Color(tempcol[0],tempcol[1],tempcol[2]);
                        screen.paint(i,j,createdColor);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}