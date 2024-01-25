package Textures;

import MathStuff.Vector3;

public class Texture {
    private String textureType;
    public Texture(String textureType){
        this.textureType = textureType;
    }

    public void setTextureType(String textureType) {
        this.textureType = textureType;
    }

    public double[] getColor(Vector3 point){
        double[] color = new double[3];
        if(textureType == "default"){
            color = new double[]{1,1,1};//just default colour
        }else if(textureType == "checkerboard"){
            color = SolidTextures.checkerBoard(point);//checkerboard texture
        }else if(textureType == "marble"){
            color = SolidTextures.marblePattern(point);//marble texture
        }
        return color;
    }

}
