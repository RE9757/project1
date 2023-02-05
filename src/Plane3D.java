
/**This class is used to contain the information of a 3D plane
 * It can calculate the Cartesian equation of a plane by giving 3 point
 * Or by giving a b c d values of Cartesian equation directly.*/
public class Plane3D {

    /**these double values of a b c d means the value of Cartesian equation(ax+by+cz+d=0)
     * which is able to express a 3D plane*/
    private double a,b,c,d;

    /**This constructor is applied when Plane3D class need to calculate Cartesian equation by three given points*/
    public Plane3D(Point3D p1, Point3D p2, Point3D p3){
        double[] v1 = new double[3];
        double[] v2 = new double[3];
        double[] v3 = new double[3];

        v1[0] = p2.getX()- p1.getX();
        v1[1] = p2.getY()- p1.getY();
        v1[2] = p2.getZ()- p1.getZ();

        v2[0] = p3.getX()- p1.getX();
        v2[1] = p3.getY()- p1.getY();
        v2[2] = p3.getZ()- p1.getZ();

        v3[0] = p3.getX()- p2.getX();
        v3[1] = p3.getY()- p2.getY();
        v3[2] = p3.getZ()- p2.getZ();
        if(p1.equals(p2)|| p1.equals(p3)||p2.equals(p3)){
            System.out.println("Point cannot be the same, reRandom!");
        } else if (
                (v2[0]/v1[0]-v2[1]/v1[1] < 0.001) && (v2[0]/v1[0]-v2[2]/v1[2] < 0.001) && (v2[1]/v1[1]-v2[2]/v1[2] < 0.001)
        ) {
            throw new ArithmeticException("The three points are in the same line!");
        } else {
            //a=(y2-y1)*(z3-z1)-(y3-y1)*(z2-z1)
            this.a=(v1[1])*(v2[2])-(v2[1])*(v1[2]);
            //b=(x3-x1)*(z2-z1)-(x2-x1)*(z3-z1)
            this.b=(v2[0])*(v1[2])-(v1[0])*(v2[2]);
            //c=(x2-x1)*(y3-y1)-(y2-y1)*(x3-x1)
            this.c=(v1[0])*(v2[1])-(v1[1])*(v2[0]);
            //d=(-a*x1-b*y1-c*z1);
            this.d=(-a*p1.getX()-b*p1.getY()-c*p1.getZ());
        }
    }

    /**creating Plane3D class by giving Cartesian equation ax+by+cz+d=0*/
    public Plane3D(double a, double b, double c, double d){
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
    }

    /**This function is used to check whether the plane has given point
     * by input the value of point to see whether it follows Cartesian equation*/
    public boolean contain(Point3D pt){
        return a*pt.getX()+b*pt.getY()+c*pt.getZ()+d==0;
    }

    /**This method is used to calculate the distance between a point and current plane*/
    public double getDistance(Point3D pt){
        double m = Math.abs((a* pt.getX()+b*pt.getY()+c*pt.getZ()+d));
        float n = (float)Math.sqrt(a*a+b*b+c*c);

        return m/n;
    }

    /**This is the toString function used to print out the Cartesian equation of current class*/
    @Override
    public String toString() {
        return a+"x + "+b+"y + "+c+"z + "+d+" = 0";
    }
}
