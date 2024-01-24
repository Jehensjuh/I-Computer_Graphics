package RayTracing;

public class PhongMaterials {
    public static Material emerald(){
        double[] ambient = {0.0215, 0.1745, 0.0215};
        double[] diffuse = {0.07568, 0.61424, 0.07568};
        double[] specular = {0.633, 0.727811, 0.633};
        double shininess = 0.6;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material jade(){
        double[] ambient = {0.135, 0.2225, 0.1575};
        double[] diffuse = {0.54, 0.89, 0.63};
        double[] specular = {0.316228, 0.316228, 0.316228};
        double shininess = 0.1;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material obsidian(){
        double[] ambient = {0.05375, 0.05, 0.06625};
        double[] diffuse = {0.18275, 0.17, 0.22525};
        double[] specular = {0.332741, 0.328634, 0.346435};
        double shininess = 0.3;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material pearl(){
        double[] ambient = {0.25, 0.20725, 0.20725};
        double[] diffuse = {1, 0.829, 0.829};
        double[] specular = {0.296648, 0.296648, 0.296648};
        double shininess = 0.088;
        double transparancy = 0.21;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material ruby(){
        double[] ambient = {0.1745, 0.01175, 0.01175};
        double[] diffuse = {0.61424, 0.04136, 0.04136};
        double[] specular = {0.727811, 0.626959, 0.626959};
        double shininess = 0.6;
        double transparancy = 0.29;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material turquoise(){
        double[] ambient = {0.1, 0.18725, 0.1745};
        double[] diffuse = {0.396, 0.74151, 0.69102};
        double[] specular = {0.297254, 0.30829, 0.306678};
        double shininess = 0.1;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

public static Material brass(){
        double[] ambient = {0.329412, 0.223529, 0.027451};
        double[] diffuse = {0.780392, 0.568627, 0.113725};
        double[] specular = {0.992157, 0.941176, 0.807843};
        double shininess = 0.21794872;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material bronze(){
        double[] ambient = {0.2125, 0.1275, 0.054};
        double[] diffuse = {0.714, 0.4284, 0.18144};
        double[] specular = {0.393548, 0.271906, 0.166721};
        double shininess = 0.2;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material chrome(){
        double[] ambient = {0.25, 0.25, 0.25};
        double[] diffuse = {0.4, 0.4, 0.4};
        double[] specular = {0.774597, 0.774597, 0.774597};
        double shininess = 0.6;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material copper(){
        double[] ambient = {0.19125, 0.0735, 0.0225};
        double[] diffuse = {0.7038, 0.27048, 0.0828};
        double[] specular = {0.256777, 0.137622, 0.086014};
        double shininess = 0.1;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material gold(){
        double[] ambient = {0.24725, 0.1995, 0.0745};
        double[] diffuse = {0.75164, 0.60648, 0.22648};
        double[] specular = {0.628281, 0.555802, 0.366065};
        double shininess = 0.4;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material silver(){
        double[] ambient = {0.19225, 0.19225, 0.19225};
        double[] diffuse = {0.50754, 0.50754, 0.50754};
        double[] specular = {0.508273, 0.508273, 0.508273};
        double shininess = 0.4;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material blackPlastic(){
        double[] ambient = {0.0, 0.0, 0.0};
        double[] diffuse = {0.01, 0.01, 0.01};
        double[] specular = {0.50, 0.50, 0.50};
        double shininess = 0.25;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular, transparancy);
    }

    public static Material cyanPlastic(){
        double[] ambient = {0.0, 0.1, 0.06};
        double[] diffuse = {0.0, 0.50980392, 0.50980392};
        double[] specular = {0.50196078, 0.50196078, 0.50196078};
        double shininess = 0.25;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular, transparancy);
    }

    public static Material greenPlastic(){
        double[] ambient = {0.0, 0.0, 0.0};
        double[] diffuse = {0.1, 0.35, 0.1};
        double[] specular = {0.45, 0.55, 0.45};
        double shininess = 0.25;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular, transparancy);
    }

    public static Material redPlastic(){
        double[] ambient = {0.0, 0.0, 0.0};
        double[] diffuse = {0.5, 0.0, 0.0};
        double[] specular = {0.7, 0.6, 0.6};
        double shininess = 0.25;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular, transparancy);
    }

    public static Material whitePlastic(){
        double[] ambient = {0.0, 0.0, 0.0};
        double[] diffuse = {0.55, 0.55, 0.55};
        double[] specular = {0.70, 0.70, 0.70};
        double shininess = 0.25;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular, transparancy);
    }

    public static Material yellowPlastic(){
        double[] ambient = {0.0, 0.0, 0.0};
        double[] diffuse = {0.5, 0.5, 0.0};
        double[] specular = {0.60, 0.60, 0.50};
        double shininess = 0.25;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material blackRubber(){
        double[] ambient = {0.02, 0.02, 0.02};
        double[] diffuse = {0.01, 0.01, 0.01};
        double[] specular = {0.4, 0.4, 0.4};
        double shininess = 0.078125;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material cyanRubber(){
        double[] ambient = {0.0, 0.05, 0.05};
        double[] diffuse = {0.4, 0.5, 0.5};
        double[] specular = {0.04, 0.7, 0.7};
        double shininess = 0.078125;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material greenRubber(){
        double[] ambient = {0.0, 0.05, 0.0};
        double[] diffuse = {0.4, 0.5, 0.4};
        double[] specular = {0.04, 0.7, 0.04};
        double shininess = 0.078125;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material redRubber(){
        double[] ambient = {0.05, 0.0, 0.0};
        double[] diffuse = {0.5, 0.4, 0.4};
        double[] specular = {0.7, 0.04, 0.04};
        double shininess = 0.078125;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material whiteRubber(){
        double[] ambient = {0.05, 0.05, 0.05};
        double[] diffuse = {0.5, 0.5, 0.5};
        double[] specular = {0.7, 0.7, 0.7};
        double shininess = 0.078125;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material yellowRubber(){
        double[] ambient = {0.05, 0.05, 0.0};
        double[] diffuse = {0.5, 0.5, 0.4};
        double[] specular = {0.7, 0.7, 0.04};
        double shininess = 0.078125;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material glass(){
        double[] ambient = {0.0001, 0.0001, 0.0001};
        double[] diffuse = {0.0001, 0.0001, 0.0001};
        double[] specular = {0.0001, 0.0001, 0.0001};
        double shininess = 0.1;
        double transparancy = 0.72;
        Material glass = new Material(ambient, shininess, diffuse, specular,transparancy);
        glass.setDc(0.55);//c2/c1
        return glass;
    }

    public static Material mirror(){
        double[] ambient = {0.0, 0.0, 0.0};
        double[] diffuse = {0.0, 0.0, 0.0};
        double[] specular = {0.0, 0.0, 0.0};
        double shininess = 0.9;
        double transparancy = 0.0;
        return new Material(ambient, shininess, diffuse, specular,transparancy);
    }

    public static Material water(){
        double[] ambient = {0.0, 0.0, 0.0};
        double[] diffuse = {0.0, 0.0, 0.0};
        double[] specular = {0.0, 0.0, 0.0};
        double shininess = 0.2;
        double transparancy = 0.8;
        Material water = new Material(ambient, shininess, diffuse, specular,transparancy);
        water.setDc(0.7519);
        return water;
    }

    public static Material sapphire(){
        double[] ambient = {0.0215, 0.1745, 0.0215};
        double[] diffuse = {0.07568, 0.61424, 0.07568};
        double[] specular = {0.633, 0.727811, 0.633};
        double shininess = 0.6;
        double transparancy = 0.6;
        Material sapphire = new Material(ambient, shininess, diffuse, specular,transparancy);
        sapphire.setDc(0.5650);
        return sapphire;
    }

    public static Material air(){
        double[] ambient = {0.0, 0.0, 0.0};
        double[] diffuse = {0.0, 0.0, 0.0};
        double[] specular = {0.0, 0.0, 0.0};
        double shininess = 0.0;
        double transparancy = 1.0;
        Material air = new Material(ambient, shininess, diffuse, specular,transparancy);
        air.setDc(0.9997);
        return air;
    }

}