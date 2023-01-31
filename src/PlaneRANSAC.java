
import java.lang.Math;
import java.util.Iterator;


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
            }
            //here
        }
    }
}






