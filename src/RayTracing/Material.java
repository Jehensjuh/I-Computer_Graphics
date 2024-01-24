package RayTracing;

public class Material {

    private double[] ambient;
    private double specularExponent;
    private double[] diffuse;
    private double[] specular;
    private double transparency;

    private double shininess;

    private double dc; //difference in lightspeed between the material and air;

    public Material(double[] ambient, double specularExponent, double[] diffuse, double[] specular, double transparency){
        this.ambient = ambient;
        this.shininess = specularExponent;
        this.diffuse = diffuse;
        this.specular = specular;
        this.specularExponent = shininess*128;
        this.transparency = transparency;
        this.dc = 0;
    }


    public void setTransparency(double transparency) {
    	this.transparency = transparency;
    }

    public void setDc(double dc) {
    	this.dc = dc;
    }

    public double getDc(){
        	return dc;
    }


    public double getTransparency() {
    	return this.transparency;
    }

    public void setShininess(double shininess) {
        this.shininess = shininess;
    }

    public double getShininess() {
        return shininess;
    }

    public double[] getAmbient(){
        return this.ambient;
    }

    public double[] getSpecular(){
        return this.specular;
    }

    public void setSpecular(double[] specular){
        this.specular = specular;
    }

    public double getSpecularExponent(){
        return this.specularExponent;
    }

    public double[] getDiffuse(){
        return this.diffuse;
    }

    public void setAmbient(double[] ambient){
        this.ambient = ambient;
    }

    public void setSpecularExponent(double specular){
        this.specularExponent = specular;
    }

    public void setDiffuse(double[] diffuse){
        this.diffuse = diffuse;
    }
}
