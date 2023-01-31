public class Point3D {

    private double X,Y,Z;

    public Point3D(double X, double Y, double Z){
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public double getZ(){
        return Z;
    }

    public boolean equals(Point3D pt){
        return (X==pt.getX())&&(Y== pt.getY())&&(Z==pt.getZ());
    }

    @Override
    public String toString() {
        return X+"\t"+Y+"\t"+Z;
    }
}
