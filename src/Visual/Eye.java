package Visual;

import MathStuff.Transformations;
import MathStuff.Vector3;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Eye implements KeyListener {
    //this class needs to allow the user to move the eye around
    //this means looking from left to right, up and down and turning around
    private double x, y, z, Uc,Vc, N;

    private double directionx, directiony, directionz;
    private double theta, phi, gamma;
    private boolean isyawed, ispitched, isrolled;

    private int counterZ, counterS, counterQ, counterD, counterA, counterE, counterX, counterC, counterR, counterT, counterU, counterI;



    public Eye(double x, double y, double z, double Uc, double Vc, double N){
        this.x = x;
        this.y = y;
        this.z = z;
        this.theta = 0;
        this.phi = 0;
        this.gamma = 0;
        this.Uc = Uc;
        this.Vc = Vc;
        this.N = N;
        this.directionx = Uc;
        this.directiony = Vc;
        this.directionz = -N;
        this.isyawed= false;
        this.ispitched = false;
        this.isrolled = false;
        this.counterZ = 0;
        this.counterS = 0;
        this.counterQ = 0;
        this.counterD = 0;
        this.counterA = 0;
        this.counterE = 0;
        this.counterX = 0;
        this.counterC = 0;
        this.counterR = 0;
        this.counterT = 0;
        this.counterU = 0;
        this.counterI = 0;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ(){
        return z;
    }

    public void setUc(double Uc){
        this.Uc = Uc;
        this.directionx = Uc;
    }

    public void setVc(double Vc){
        this.Vc = Vc;
        this.directiony = Vc;
    }

    public void setN(double N){
        this.N = N;
        this.directionz = -N;
    }

    public double getDirectionx(){
        return directionx;
    }

    public double getDirectiony(){
        return directiony;
    }

    public double getDirectionz(){
        return directionz;
    }

    public Vector3 getDirection(){
        return new Vector3(directionx, directiony, directionz, "vector");
    }

    public Vector3 getPosition(){
        return new Vector3(x, y, z, "point");
    }

    public void transform(){
        if(isyawed){
            double[][] yawMatrix = Transformations.yRotationMatrix(theta);
            double[] transform = {yawMatrix[0][0]*directionx+ yawMatrix[0][1]*directiony+yawMatrix[0][2]*directionz+yawMatrix[0][3]*0,
                    yawMatrix[1][0]*directionx+ yawMatrix[1][1]*directiony+yawMatrix[1][2]*directionz+yawMatrix[1][3]*0,
                    yawMatrix[2][0]*directionx+ yawMatrix[2][1]*directiony+yawMatrix[2][2]*directionz+yawMatrix[2][3]*0,
                    yawMatrix[3][0]*directionx+ yawMatrix[3][1]*directiony+yawMatrix[3][2]*directionz+yawMatrix[3][3]*0};
            directionx = transform[0];
            directiony = transform[1];
            directionz = transform[2];
        }
        if(ispitched){
            double[][] pitchMatrix = Transformations.xRotationMatrix(phi);
            double[] transform = {pitchMatrix[0][0]*directionx+ pitchMatrix[0][1]*directiony+pitchMatrix[0][2]*directionz+pitchMatrix[0][3]*0,
                    pitchMatrix[1][0]*directionx+ pitchMatrix[1][1]*directiony+pitchMatrix[1][2]*directionz+pitchMatrix[1][3]*0,
                    pitchMatrix[2][0]*directionx+ pitchMatrix[2][1]*directiony+pitchMatrix[2][2]*directionz+pitchMatrix[2][3]*0,
                    pitchMatrix[3][0]*directionx+ pitchMatrix[3][1]*directiony+pitchMatrix[3][2]*directionz+pitchMatrix[3][3]*0};
            directionx = transform[0];
            directiony = transform[1];
            directionz = transform[2];
        }
        if(isrolled){
            double[][] rollMatrix = Transformations.zRotationMatrix(gamma);
            double[] transform = {rollMatrix[0][0]*directionx+ rollMatrix[0][1]*directiony+rollMatrix[0][2]*directionz+rollMatrix[0][3]*0,
                    rollMatrix[1][0]*directionx+ rollMatrix[1][1]*directiony+rollMatrix[1][2]*directionz+rollMatrix[1][3]*0,
                    rollMatrix[2][0]*directionx+ rollMatrix[2][1]*directiony+rollMatrix[2][2]*directionz+rollMatrix[2][3]*0,
                    rollMatrix[3][0]*directionx+ rollMatrix[3][1]*directiony+rollMatrix[3][2]*directionz+rollMatrix[3][3]*0};
            directionx = transform[0];
            directiony = transform[1];
            directionz = transform[2];
        }
    }

    public void yaw(double angle){
        theta += angle;
        if(theta == 0){
            isyawed = false;
        }else{
            isyawed = true;
        }
    }

    public void pitch(double angle){
        phi += angle;
        if(phi == 0){
            ispitched = false;}
        else{
            ispitched = true;
        }

    }

    public void roll(double angle){
        gamma += angle;
        if(gamma == 0){
            isrolled = false;}
        else{
            isrolled = true;
        }
    }

    public void slide(String direction){
        switch(direction){
            case "up":
                y += 0.5;
                break;
            case "down":
                y -= 0.5;
                break;
            case "left":
                x -= 0.5;
                break;
            case "right":
                x += 0.5;
                break;
            case "forward":
                z -= 0.5;
                break;
            case "backward":
                z += 0.5;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_Z){
            if(counterZ < 3){
                counterZ++;
            } else if (counterZ == 3){
                slide("up");
                counterZ = 0;
            }
        }
        if(keyCode == KeyEvent.VK_S){
            if(counterS < 3){
                counterS++;
            } else if (counterS == 3){
                slide("down");
                counterS = 0;
            }
        }
        if(keyCode == KeyEvent.VK_Q){
            if(counterQ < 3){
                counterQ++;
            } else if (counterQ == 3){
                slide("left");
                counterQ = 0;
            }
        }
        if(keyCode == KeyEvent.VK_D){
            if(counterD < 3){
                counterD++;
            } else if (counterD == 3){
                slide("right");
                counterD = 0;
            }
        }
        if(keyCode == KeyEvent.VK_A){
            if(counterA < 3){
                counterA++;
            } else if (counterA == 3){
                yaw(Math.toRadians(5));
                counterA = 0;
            }
        }
        if(keyCode == KeyEvent.VK_E){
            if(counterE < 3){
                counterE++;
            } else if (counterE == 3){
                yaw(Math.toRadians(-5));
                counterE = 0;
            }
        }
        if(keyCode == KeyEvent.VK_X){
            if(counterX < 3){
                counterX++;
            } else if (counterX == 3){
                slide("forward");
                counterX = 0;
            }
        }
        if(keyCode == KeyEvent.VK_C){
            if(counterC < 3){
                counterC++;
            } else if (counterC == 3){
                slide("backward");
                counterC = 0;
            }
        }
        if(keyCode == KeyEvent.VK_R){
            if(counterR < 3){
                counterR++;
            } else if (counterR == 3){
                roll(Math.toRadians(5));
                counterR = 0;
            }
        }
        if(keyCode == KeyEvent.VK_T){
            if(counterT < 3){
                counterT++;
            } else if (counterT == 3){
                roll(Math.toRadians(-5));
                counterT = 0;
            }
        }
        if(keyCode == KeyEvent.VK_U){
            if(counterU < 3){
                counterU++;
            } else if (counterU == 3){
                pitch(Math.toRadians(5));
                counterU = 0;
            }
        }
        if(keyCode == KeyEvent.VK_I){
            if(counterI < 3){
                counterI++;
            } else if (counterI == 3){
                pitch(Math.toRadians(-5));
                counterI = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
