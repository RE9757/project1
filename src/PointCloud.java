import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;
import java.io.File;  // Import the File class

import static java.lang.Math.random;

public class PointCloud {

    private LinkedList<Point3D> file;

    public PointCloud(){}
    public PointCloud(String filename){
        this.file = ReadInFile(filename+".xyz");
    }

    public void addPoint(Point3D pt){
        file.add(pt);
    }

    public Point3D getPoint(){
        if(file.size()!=0){
            int randomNum = ThreadLocalRandom.current().nextInt(0, file.size() + 1);
            return file.get(randomNum);
        }else {
            System.out.println("File is empty, please check file validation");
            return null;
        }
    }

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

    public LinkedList<Point3D> getFile() {
        return file;
    }

    public void setFile(LinkedList<Point3D> file){this.file=file;}

    public void remove(Point3D pt){
        file.remove(pt);
    }

    public Iterator<Point3D> iterator(){
        return file.iterator();
    }

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
