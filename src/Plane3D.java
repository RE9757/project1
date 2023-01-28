public class Plane3D {

    private double a,b,c,d;

    public Plane3D(Point3D p1, Point3D p2, Point3D p3){
        //a=(y2-y1)*(z3-z1)-(y3-y1)*(z2-z1)
        this.a=(p2.getY()-p1.getY())*(p3.getZ()-p1.getZ())-(p3.getY()-p1.getY())*(p2.getZ()-p1.getZ());
        //b=(x3-x1)*(z2-z1)-(x2-x1)*(z3-z1)
        this.b=(p3.getX()-p1.getX())*(p2.getZ()-p1.getZ())-(p2.getX()-p1.getX())*(p3.getZ()-p1.getZ());
        //c=(x2-x1)*(y3-y1)-(y2-y1)*(x3-x1)
        this.c=(p2.getX()- p1.getX())*(p3.getY()-p1.getY())-(p2.getY()-p1.getY())*(p3.getX()-p1.getX());
        //d=(-a*x1-b*y1-c*z1);
        this.d=(-a*p1.getX()-b*p1.getY()-c*p1.getZ());
    }

    public Plane3D(double a, double b, double c, double d){
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
    }

    public double getDistance(Point3D pt){
        double m = Math.abs((a* pt.getX()+b*pt.getY()+c*pt.getZ()+d));
        float n = (float)Math.sqrt(a*a+b*b+c*c);

        return m/n;
    }


    @Override
    public String toString() {
        return a+"x + "+b+"y + "+c+"z + "+d+" = 0";
    }
}
