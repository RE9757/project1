import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        //Point3D a = new Point3D(-1,2,1);
        //Point3D b = new Point3D(0,-3,2);
        //Point3D c = new Point3D(1,1,-4);

        //Plane3D p = new Plane3D(a,b,c);
        //System.out.println(p);
//        Plane3D f = new Plane3D(2,-2,5,8);
//        Point3D q = new Point3D(4,-4,3);
//
//        System.out.println(f.getDistance(q));
//        PointCloud pc = new PointCloud("src/PointCloud1.xyz");
//        Iterator<Point3D> point3DIterator = pc.iterator();
//        for(int i =0; i<100; i++){
//            System.out.println(point3DIterator.next());
//        }
        PointCloud pc =new PointCloud("src/PointCloud1");
        PlaneRANSAC planeRANSAC = new PlaneRANSAC(pc);
        Plane3D plane3D = planeRANSAC.getRandomPlane3D();
        double p=planeRANSAC.getPercentageOfPointsOnPlane(plane3D);
        int k= planeRANSAC.getNumberOfIterations(0.99,p);
        System.out.println(k);

        planeRANSAC.run(k,"src/PointCloud1");
    }
}