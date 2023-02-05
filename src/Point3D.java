
/**This point is used to contain the information of a 3D point
 * it has three attribution*/
public class Point3D {

    /**double X, Y, Z express the point*/
    private double X,Y,Z;

    /**constructor of 3d point, double x,y,z*/
    public Point3D(double X, double Y, double Z){
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }

    /**this function returns the x value of this 3d point*/
    public double getX() {
        return X;
    }

    /**this function returns the y value of this 3d point*/
    public double getY() {
        return Y;
    }

    /**this function returns the z value of this 3d point*/
    public double getZ(){
        return Z;
    }

    /**check whether one point is this point*/
    public boolean equals(Point3D pt){
        return (X==pt.getX())&&(Y== pt.getY())&&(Z==pt.getZ());
    }

    /**print a point*/
    @Override
    public String toString() {
        return X+"\t"+Y+"\t"+Z;
    }
}
