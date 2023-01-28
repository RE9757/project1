public class Main {
    public static void main(String[] args) {
        //Point3D a = new Point3D(-1,2,1);
        //Point3D b = new Point3D(0,-3,2);
        //Point3D c = new Point3D(1,1,-4);

        //Plane3D p = new Plane3D(a,b,c);
        //System.out.println(p);
        Plane3D f = new Plane3D(2,-2,5,8);
        Point3D q = new Point3D(4,-4,3);

        System.out.println(f.getDistance(q));

    }
}