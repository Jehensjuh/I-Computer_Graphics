package Visual;

import MathStuff.Vector3;

public class LightSource {
    private Vector3 position;
    private double intensity;

    private Vector3 color;


    public LightSource(double x, double y, double z, double intensity) {
        this.position = new Vector3(x, y, z, "point");
        this.intensity = intensity;
        this.color = new Vector3(1,1,1,"vector");
    }

    public Vector3 getColor() {
        return this.color;
    }

    public void setColor(int r, int g, int b) {
        this.color = new Vector3(r,g,b,"vector");
    }
    public Vector3 getPosition() {
        return this.position;
    }

    public void setPosition(double x, double y, double z) {
        this.position = new Vector3(x, y, z, "point");
    }

    public double getIntensity() {
        return this.intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }


}
