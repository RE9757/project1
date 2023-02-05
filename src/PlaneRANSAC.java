
import java.lang.Math;
import java.util.Iterator;
import java.util.LinkedList;

/**This class contains method to calculate and save files into .xyz form.
 * First this class need insert a PointCloud by constructor
 * Secondly to generate a randomPlane from given pc by getRandomPlane3D() method
 * Then use getPercentageOfPointsOnPlane(plane3D) method to calculate the number of percentage support the given plane
 * and calculate the number of iterations by getNumberOfIterations() method(default eps is 0.5, and default confidence is 0.99)
 * Finally use run method to repeat algorithm by NumberOfIteration of times, save and remove all the points on dominate plane(by calculating 3D Cartesian equation.)
 * */
public class PlaneRANSAC {

    /**eps value, used to calculate the supports from plane*/
    private double eps;

    /**the PointCloud pc*/
    private PointCloud pc;

    /**the list of point in PointCloud pc support randomPlane*/
    private LinkedList<Point3D> bestSupportPtList;

    /**the number of point support (randomPlane)plane*/
    private int bestSupport;

    /**A random plane from point cloud*/
    private Plane3D randomPlane;

    /**the best supported plane(current dominate plane)*/
    private Plane3D bestSupportPlane;

    /**value of confidence rate(could be modified by set method and getIterationNumber method)*/
    private double confidence;

    /**constructor of PlaneRANSAC class
     * generate default value for eps=0.5 and dominate plane is 0,0,0,0*/
    public PlaneRANSAC(PointCloud pc){
        confidence = 0.99;
        this.pc = pc;
        bestSupport = 0;
        eps = 0.5;
        bestSupportPtList = new LinkedList<>();
        bestSupportPlane = new Plane3D(0,0,0,0);
    }

    /**set the eps value into the class*/
    public void setEps(double eps){
        this.eps = eps;
    }

    /**get the eps value*/
    public double getEps(){
        return eps;
    }

    /**set a confidence value to this class */
    public void setConfidence(double confidence){this.confidence=confidence;}

    /**get confidence value in this class */
    public double getConfidence(){return confidence;}


    /**calculate what is the percentage of points support plane*/
    public double getPercentageOfPointsOnPlane(Plane3D plane3D){
        double result = (double) (getSupports(plane3D).size())/(double) (pc.getFile().size());
        return result;
    }


    /**calculate the number of iterations by given confidence and percentage of points on plane */
    public int getNumberOfIterations(double confidence, double percentageOfPointsOnPlane){
        double k=0;
        this.confidence=confidence;
        try{
        k=(Math.log10(1-confidence))/(Math.log10(1-percentageOfPointsOnPlane*percentageOfPointsOnPlane*percentageOfPointsOnPlane));
        } catch (ArithmeticException e){
            System.out.println("Calculation error due to wrong division");
        }
        return (int)k;
    }

    /**this function will calculate and add support from PointCloud pc to a given plane3D
     * if PointCloud pc has empty file, then this function will return null*/
    public LinkedList<Point3D> getSupports(Plane3D plane3D){
        if(pc.getFile().size()==0){
            return null;
        } else {
        LinkedList<Point3D> ptList = new LinkedList<>();
        for ( Point3D pt: pc.getFile()) {
            if(plane3D.getDistance(pt)<=eps){
                ptList.add(pt);
            }
        }
        return ptList;
        }
    }

    /**This function will get random three point by PointCloud pc and pc.getPoint(). If pc is empty, p1,p2,p3 might be null
     * then this method will print an error message */
    public Plane3D getRandomPlane3D(){
        Point3D p1,p2,p3;
        p1 = pc.getPoint();
        p2 = pc.getPoint();
        p3 = pc.getPoint();
        if(p1 != null && p2 != null && p3 != null){
        while(p2.equals(p1)){
            p2 = pc.getPoint();
        }
        while(p3.equals(p2)||p3.equals(p1)){
            p3 = pc.getPoint();
        }//get three random points and make sure they are not the same as each other

        Plane3D plane = new Plane3D(p1,p2,p3);//create a corresponding plane
        this.randomPlane = plane;
        return plane;}else{
            System.out.println("Given point cloud has problem, unable to generate random plane!");
            return null;
        }
    }

    /**Process the steps of numberOfIterations times to find dominate plane from filename, remove the points
     * belong to dominate plane(by cartesian equation) and save(this process will repeat 3 time) */
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

    //public LinkedList<Point3D> getBestSupportPtList(){return bestSupportPtList;}
}






