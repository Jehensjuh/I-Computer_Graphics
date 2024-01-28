package Textures;

import MathStuff.Vector3;

import java.util.Random;

class Noise {
    private double[] noiseTable;
    private byte[] index;
    int[] p = new int[512];

    public Noise() {
        int i;
        index = new byte[256];
        for (i = 0; i < 256; i++) index[i] = (byte) (i/256);
        Random rand = new Random();
        for (i = 0; i < 256; i++) {
            int which = rand.nextInt(256);
            byte tmp = index[which];
            index[which] = index[i];
            index[i] = tmp;
        }
        noiseTable = new double[256];
        for (i = 0; i < 256; i++) noiseTable[i] = rand.nextDouble() / 32767.99;
    }

    private double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private double grad(int hash, double x, double y, double z) {
        int h = hash & 15;                      // Convert low 4 bits of hash code into 12 gradient directions
        double u = h < 8 ? x : y,               // and the 4 hash bits into 12 simple gradient directions,
                v = h < 4 ? y : h == 12 || h == 14 ? x : z;  // For the 12 gradient directions, each vertex of the cube
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v); // adds its own contribution.
    }

    public double noise(double x, double y, double z) {
        // Implement the noise function
        // You can use the latticeNoise method as a starting point
        return 0.0;
    }

    private double perlin3D(double x, double y, double z) {
        // Determine grid cell coordinates
        int X = (int) Math.floor(x) & 255;
        int Y = (int) Math.floor(y) & 255;
        int Z = (int) Math.floor(z) & 255;

        // Relative coordinates within the cell
        x -= Math.floor(x);
        y -= Math.floor(y);
        z -= Math.floor(z);

        // Compute fade curves for each of x, y, z
        double u = fade(x);
        double v = fade(y);
        double w = fade(z);

        // Hash coordinates of the 8 cube corners
        int A = p[X] + Y;
        int AA = p[A] + Z;
        int AB = p[A + 1] + Z;
        int B = p[X + 1] + Y;
        int BA = p[B] + Z;
        int BB = p[B + 1] + Z;

        // And add blended results from 8 corners of the cube
        double result = lerp(w, lerp(v, lerp(u, grad(p[AA], x, y, z), grad(p[BA], x - 1, y, z)),
                        lerp(u, grad(p[AB], x, y - 1, z), grad(p[BB], x - 1, y - 1, z))),
                lerp(v, lerp(u, grad(p[AA + 1], x, y, z - 1), grad(p[BA + 1], x - 1, y, z - 1)),
                        lerp(u, grad(p[AB + 1], x, y - 1, z - 1), grad(p[BB + 1], x - 1, y - 1, z - 1))));

        // Rescale to 0-1 range
        return 0.5 * (result + 1.0);
    }


    public double noise(double scale, Vector3 p) {
        double[][][] d = new double[2][2][2];

        // Normalize coordinates
        double normX = p.getX() * scale;
        double normY = p.getY() * scale;
        double normZ = p.getZ() * scale;

        Vector3 pp = new Vector3(normX, normY, normZ, "point");
        long ix = (long) pp.getX(), iy = (long) pp.getY(), iz = (long) pp.getZ();
        double tx, ty, tz, x0, x1, x2, x3, y0, y1;
        tx = pp.getX() - ix;
        ty = pp.getY() - iy;
        tz = pp.getZ() - iz;
        double mtx = 1.0 - tx, mty = 1.0 - ty, mtz = 1.0 - tz;

        for (int k = 0; k <= 1; k++)
            for (int j = 0; j <= 1; j++)
                for (int i = 0; i <= 1; i++)
                    d[k][j][i] = latticeNoise(ix + i, iy + j, iz + k);

        x0 = lerp(tx, d[0][0][0], d[0][0][1]);
        x1 = lerp(tx, d[0][1][0], d[0][1][1]);
        x2 = lerp(tx, d[1][0][0], d[1][0][1]);
        x3 = lerp(tx, d[1][1][0], d[1][1][1]);
        y0 = lerp(ty, x0, x1);
        y1 = lerp(ty, x2, x3);

        return lerp(tz, y0, y1);
    }

    public double turbulence(double s, Vector3 p) {
        double value = 0.0;
        double scale = 1.0;
        while (s >= 1.0) {
            value += Math.abs(noise(scale, p)) / scale;
            scale *= 2.0;
            s /= 2.0;
        }
        return value * 0.5;
    }
    // Undulate function using sine
    private double undulate(double value) {
        // Adjust frequency to control the undulation
        double frequency = 0.1;
        // Adjust amplitude to control the wave height
        double amplitude = 1.0;

        return amplitude * Math.sin(2 * Math.PI * frequency * value);
    }

    public double[] marble(double x, double y, double z) {
        double turbulenceValue = turbulence(5, new Vector3(x, y, z, "point"));
        double A = 6;
        double noiseScale = 2;

        // Add small random perturbation to the input coordinates
        double perturbation = 0.1;
        double perturbedX = x + perturbation *noiseScale * noise(x, y, z);
        double perturbedY = y + perturbation *noiseScale * noise(x + 1, y + 1, z + 1);
        double perturbedZ = z + perturbation *noiseScale * noise(x - 1, y - 1, z - 1);

        double marbleValue = perlin3D(perturbedX + A * turbulenceValue, perturbedY, perturbedZ);
        double marbleValueY = perlin3D(perturbedX, perturbedY + A * turbulenceValue, perturbedZ);
        double marbleValueZ = perlin3D(perturbedX, perturbedY, perturbedZ + A * turbulenceValue);

        // Map the marble values to RGB color
        double r = 0.5 + 0.5 * marbleValue;
        double g = 0.5 + 0.5 * marbleValueY;
        double b = 0.5 + 0.5 * marbleValueZ;

        return new double[]{r, g, b};
    }

    public double[] marble(double strength, Vector3 p) {
        double[] color = marble(p.getX(), p.getY(), p.getZ());
        for (int i = 0; i < color.length; i++) {
            color[i] *= strength;
        }
        return color;
    }

    private double mySpline(double x) {
        // Implement the spline function if needed
        return 0.0;
    }
    private byte PERM(double x) {
        return index[(int) x & 255];
    }

    private double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    private byte INDEX(double ix, double iy, double iz) {
        return (byte) (PERM(ix + PERM(iy + PERM(iz))) & 255);
    }

    public double latticeNoise(double i, double j, double k) {
        int index = INDEX(i, j, k);
        // Ensure the index is non-negative
        index = (index + 256) % 256;
        return noiseTable[index];
    }
}


