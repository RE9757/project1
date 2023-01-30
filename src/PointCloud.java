import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

import static java.lang.Math.random;

public class PointCloud {

    private LinkedList<Point3D> file;

    public PointCloud(){}
    public PointCloud(String filename){
        this.file = ReadInFile(filename);
    }

    public void addPoint(Point3D pt){
        file.add(pt);
    }

    public Point3D getPoint(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, file.size() + 1);
        return file.get(randomNum);
    }

    public void save(String filename){

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
                //StringTokenizer st = new StringTokenizer(line);
                //while (st.hasMoreTokens()){
                    //list.add(st.nextToken());
                //}
                String[] s = line.split(" ");
                if(count == 0){

                }else{
                    Point3D p = new Point3D(Double.parseDouble(s[0]),Double.parseDouble(s[1]),Double.parseDouble(s[2]));
                    list.add(p);
                }
                count++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
