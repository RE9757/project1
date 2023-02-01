
import java.lang.Math;
import java.util.Iterator;
import java.util.LinkedList;


public class PlaneRANSAC {

    private double eps;
    private PointCloud pc;

    public PlaneRANSAC(PointCloud pc){
        this.pc = pc;
    }

    public void setEps(double eps){
        this.eps = eps;

    }

    public double getEps(){
        return eps;
    }

    public int getNumberOfIterations(double confidence, double percentageOfPointsOnPlane){
        double k;
        k=Math.log(1-confidence)/Math.log(1-percentageOfPointsOnPlane*percentageOfPointsOnPlane*percentageOfPointsOnPlane);
        return (int)k;
    }

    public void run(int numberOfIterations, String filename){

        int maxNumberOfSupport=0;//need more attributes!!


        PointCloud pc = new PointCloud(filename);
        for(int i=0; i<numberOfIterations;i++){
            Point3D p1,p2,p3;
            p1 = pc.getPoint();
            p2 = pc.getPoint();
            p3 = pc.getPoint();
            while(p2.equals(p1)){
                p2 = pc.getPoint();
            }
            while(p3.equals(p2)||p3.equals(p1)){
                p3 = pc.getPoint();
            }//get three random points and make sure they are not the same as each other

            Plane3D plane = new Plane3D(p1,p2,p3);//create a corresponding plane

            Iterator<Point3D> point3DIterator = pc.iterator();

            while(point3DIterator.hasNext()){
                int numberOfSupport=0;
                double distance = plane.getDistance(point3DIterator.next());
                if (distance < eps){
                    numberOfSupport++;
                }//here step5
            }
        }
    }
}






