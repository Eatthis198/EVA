package uebung2;

import util.HexReader;

import java.io.*;

/**
 * Created by David on 04.04.2017.
 */
public class Point implements Serializable {

    private int x, y;

    private Point b;

    private static int serialVersionUID = 43981;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setB(Point newB){
        b = newB;
    }

    private static Object deepCopy(Object src){

        Object dst = null;

        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);) {

            oos.writeObject(src);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);

            dst = ois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dst;
    }

    public static void main(String args[]) {

        final String file = "Point.txt";

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file));
             ObjectInputStream ois = new ObjectInputStream(
                     new FileInputStream(file));) {

            Point p = new Point(1, 2);
            Point b = new Point(5,6);
            p.setB(b);

            oos.writeObject(p);
            b.x = 7;
            oos.writeObject(b);

            Point p2 = (Point) ois.readObject();
            Point b2 = (Point) ois.readObject();

            System.out.println("P.x = " + p.x + ", P.y = " + p.y + " p.b = (" + p.b.x
                    + "," + p.b.y + ")");

            HexReader.getInstance().readFile(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
