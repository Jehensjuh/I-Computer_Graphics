package Textures;

import java.util.Random;

import static java.lang.Math.abs;

public class Noise {
    private byte[] index;
    private float[] noiseTable;

    public Noise() {
        initializeIndex();
        initializeNoiseTable();
    }

    private void initializeIndex() {
        index = new byte[256];
        for (int i = 0; i < 256; i++) {
            index[i] = (byte) i;
        }

        Random random = new Random();
        for (int i = 0; i < 256; i++) {
            int which = random.nextInt(256);
            byte tmp = index[which];
            index[which] = index[i];
            index[i] = tmp;
        }
    }

    private void initializeNoiseTable() {
        noiseTable = new float[256];
        Random random = new Random();
        for (int i = 0; i < 256; i++) {
            noiseTable[i] = random.nextFloat() / 32767.99f;
        }
    }

    private byte Perm(double x) {
        return index[(int) Math.abs(x) & 255];
    }

    private float Index(double x, double y, double z) {
        int xi = (int) Math.abs(x) & 255;
        int yi = (int) Math.abs(y) & 255;
        int zi = (int) Math.abs(z) & 255;

        return noiseTable[(Perm(xi) + Perm(yi) + Perm(zi)) & 255];
    }

    public double latticeNoise(double x, double y, double z) {
        return Index(x, y, z);
    }


}
