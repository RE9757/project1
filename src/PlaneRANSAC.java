
import java.lang.Math;
import java.util.Iterator;
import java.util.LinkedList;


public class PlaneRANSAC {

    private double eps;
    private PointCloud pc;

    private LinkedList<Point3D> bestSupportPtList;

    private int bestSupport;

    private Plane3D randomPlane;

    private Plane3D bestSupportPlane;

    private double confidence;

    public PlaneRANSAC(PointCloud pc){
        confidence = 0;
        this.pc = pc;
        bestSupport = 0;
        eps = 5;
        bestSupportPtList = new LinkedList<Point3D>();
        bestSupportPlane = new Plane3D(0,0,0,0);
    }

    public void setEps(double eps){
        this.eps = eps;
    }

    public double getEps(){
        return eps;
    }

    public void setConfidence(double confidence){this.confidence=confidence;}

    public double getConfidence(){return confidence;}

    public double getPercentageOfPointsOnPlane(Plane3D plane3D){
        double result = (double) (getSupports(plane3D).size())/(double) (pc.getFile().size());
        System.out.println("getSupports(plane3D).size()"+getSupports(plane3D).size());
        System.out.println("pc.getFile().size()"+pc.getFile().size());
        System.out.println("result"+result);
        return result;
    }

    public int getNumberOfIterations(double confidence, double percentageOfPointsOnPlane){
        double k;
        this.confidence=confidence;
        k=(Math.log10(1-confidence))/(Math.log10(1-percentageOfPointsOnPlane*percentageOfPointsOnPlane*percentageOfPointsOnPlane));
        return (int)k;
    }

    public LinkedList<Point3D> getSupports(Plane3D plane3D){
        LinkedList<Point3D> ptList = new LinkedList<>();
        for ( Point3D pt: pc.getFile()) {
            if(plane3D.getDistance(pt)<=eps){
                ptList.add(pt);
            }
        }
        System.out.println(ptList.size());
        return ptList;
    }

    public Plane3D getRandomPlane3D(){
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
        this.randomPlane = plane;
        return plane;
    }

    public void run(int numberOfIterations, String filename){

        this.pc = new PointCloud(filename);

        for(int i = 0; i < numberOfIterations; i++){
            LinkedList<Point3D> temp = new LinkedList<>();
            temp = getSupports(randomPlane);
            if(temp.size()>bestSupport){
                bestSupport=temp.size();
                bestSupportPtList=temp;
                bestSupportPlane = randomPlane;
            }
            this.randomPlane = getRandomPlane3D();
        }

        PointCloud p1 = new PointCloud();
        p1.setFile(bestSupportPtList);
        p1.save(filename+"_p1.xyz");

        for(Point3D pt: bestSupportPtList){
            if(bestSupportPlane.contain(pt)){pc.remove(pt);}
        }



        this.randomPlane = getRandomPlane3D();
        double p = getPercentageOfPointsOnPlane(randomPlane);
        numberOfIterations = getNumberOfIterations(confidence,p);

        bestSupport = 0;
        bestSupportPtList = new LinkedList<>();

        for(int i = 0; i < numberOfIterations; i++){
            LinkedList<Point3D> temp = new LinkedList<>();
            temp = getSupports(randomPlane);
            if(temp.size()>bestSupport){
                bestSupport=temp.size();
                bestSupportPtList=temp;
                bestSupportPlane = randomPlane;
            }
            this.randomPlane=getRandomPlane3D();
        }

        PointCloud p2 = new PointCloud();
        p2.setFile(bestSupportPtList);
        p2.save(filename+"_p2.xyz");

        for(Point3D pt: bestSupportPtList){
            if(bestSupportPlane.contain(pt)){pc.remove(pt);}
        }

        this.randomPlane = getRandomPlane3D();
        double q = getPercentageOfPointsOnPlane(randomPlane);
        numberOfIterations = getNumberOfIterations(confidence,q);

        bestSupport = 0;
        bestSupportPtList = new LinkedList<>();

        for(int i = 0; i < numberOfIterations; i++){
            LinkedList<Point3D> temp = new LinkedList<>();
            temp = getSupports(randomPlane);
            if(temp.size()>bestSupport){
                bestSupport=temp.size();
                bestSupportPtList=temp;
                bestSupportPlane = randomPlane;
            }
            this.randomPlane=getRandomPlane3D();
        }

        PointCloud p3 = new PointCloud();
        p3.setFile(bestSupportPtList);
        p3.save(filename+"_p3.xyz");

        for(Point3D pt: bestSupportPtList){
            if(bestSupportPlane.contain(pt)){pc.remove(pt);}
        }

        pc.save(filename+"_p0.xyz");
        //save && remove points
    }

    public LinkedList<Point3D> getBestSupportPtList(){return bestSupportPtList;}
}






