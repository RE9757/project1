import java.util.Iterator;

/**Main method is used to run the algorithm*/
public class Main {
    public static void main(String[] args) {

        //create a new point cloud by path
        PointCloud pc =new PointCloud("PointCloud3");

        //create a new planeRANSAC class by the point cloud above
        PlaneRANSAC planeRANSAC = new PlaneRANSAC(pc);

        //random a new plane from point cloud
        Plane3D plane3D = planeRANSAC.getRandomPlane3D();

        //count the number of support and p%
        double p=planeRANSAC.getPercentageOfPointsOnPlane(plane3D);

        planeRANSAC.setEps(0.5);//Please input the eps value here, default value is 0.5

        //calculate the number of the most dominate plane iteration times(confidence value is 0.99 now)
        int k= planeRANSAC.getNumberOfIterations(0.99,p);

        //provide number of iterations and path to run RANSAC algorithm
        planeRANSAC.run(k,"PointCloud3");
    }
}