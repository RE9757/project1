import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        PointCloud pc =new PointCloud("src/PointCloud1");
        PlaneRANSAC planeRANSAC = new PlaneRANSAC(pc);
        Plane3D plane3D = planeRANSAC.getRandomPlane3D();
        double p=planeRANSAC.getPercentageOfPointsOnPlane(plane3D);
        int k= planeRANSAC.getNumberOfIterations(0.99,p);

        planeRANSAC.run(k,"src/PointCloud1");
    }
}