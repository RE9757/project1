import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;
import java.io.File;  // Import the File class

import static java.lang.Math.random;

/**Class PointCloud is to contain a set of 3D points from given file
 * Therefore, there is a LinkedList called file to contain Point3D*/
public class PointCloud {

    /**This attribute is used to contain points 3d*/
    private LinkedList<Point3D> file;

    /**Empty constructor*/
    public PointCloud(){}

    /**A constructor used to start a new PointCloud and read points from given file*/
    public PointCloud(String filename){
        this.file = ReadInFile(filename+".xyz");
    }

    /**This method is used to add points to current point cloud*/
    public void addPoint(Point3D pt){
        file.add(pt);
    }

    /**this method is used to random a point from current point cloud*/
    public Point3D getPoint(){
        if(file.size()!=0){
            try{
                int randomNum = ThreadLocalRandom.current().nextInt(0, file.size());//
                return file.get(randomNum);
            } catch (IndexOutOfBoundsException e){
                System.out.println("Random out of bound or file error!");
                return null;
            }
        }else {
            System.out.println("File is empty, please check file validation");
            return null;
        }
    }

    /**this method is used to save xyz file as given filename*/
    public void save(String filename){
        String context = "x\ty\tz"+"\n";
        for (Point3D p:file) {
            context += p.toString()+"\n";
        }
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(context);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**This method is to return the file(points) in point cloud*/
    public LinkedList<Point3D> getFile() {
        return file;
    }

    /**set the file of current point cloud*/
    public void setFile(LinkedList<Point3D> file){this.file=file;}

    /**remove points from current point cloud
     * this function will be used when save dominate plane and remove from point cloud*/
    public void remove(Point3D pt){
        file.remove(pt);
    }

    /**this function return a iterator of file linkedList for convenience*/
    public Iterator<Point3D> iterator(){
        return file.iterator();
    }

    /**this function is used to read the file contained in given path*/
    public LinkedList<Point3D> ReadInFile(String path){
        File file = new File(path);
        LinkedList list = new LinkedList<Point3D>();

        try {
            Scanner scanner = new Scanner(file);
            int count = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] s = line.split("\\s+");
                if(count == 0){

                }else{
                    double x = Double.parseDouble(s[0]);
                    double y = Double.parseDouble(s[1]);
                    double z = Double.parseDouble(s[2]);
                    Point3D p = new Point3D(x,y,z);
                    list.add(p);
                }
                count++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Given file not found!");
        }
        return list;
    }
}
