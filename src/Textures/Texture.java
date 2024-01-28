package Textures;

import MathStuff.Vector3;

public class Texture {
    private String textureType;
    private double amountOfRings;
    public Texture(String textureType){
        
        this.textureType = textureType;
        this.amountOfRings = 5;
    }
    
    public Texture(String textureType, double amountOfCirlcles){
        this.textureType = textureType;
        this.amountOfRings = amountOfCirlcles;
    }

    public void setTextureType(String textureType) {
        this.textureType = textureType;
    }
    public String getTextureType() {
        return textureType;
    }

    public void setAmountOfRings(double amountOfRings) {
        this.amountOfRings = amountOfRings;
    }

    public double[] getColor(Vector3 point){
        double[] color = new double[3];
        if(textureType == "default"){
            color = new double[]{1,1,1};//just default colour
        }else if(textureType == "checkerboard"){
            color = SolidTextures.checkerBoard(point);//checkerboard texture
        }else if(textureType == "marble"){
            color = SolidTextures.marblePattern(point);//marble texture
        }else if(textureType == "smooth"){
            color = SolidTextures.smooth(point);//smooth texture
        }else if(textureType == "wood") {
            color = SolidTextures.woodPattern(point, amountOfRings);//wood texture
        }else if(textureType == "trippy") {
            color = SolidTextures.trippy(point);//trippy texture
        }else if(textureType == "smiley") {
            color = PictureTexture.getColor(point, "images.png");//smiley texture
        }
        return color;
    }

}
