package uebung1;

import java.io.*;

/**
 * Created by David on 24.03.2017.
 */
public class Aufgabe4 {

    public static void main(String args[]) {

        long time = System.currentTimeMillis();

        CopyVorlesung.copyFile(args[0], args[1]);

        System.out.println("Time CopyVorlesung: " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();

        CopyBuffered.copyFile(args[0], args[1]);

        System.out.println("Time CopyBufferd: " + (System.currentTimeMillis() - time));
    }

}

class CopyVorlesung {

    public static void copyFile(String src, String dst) {
        try (FileInputStream in = new FileInputStream(src);
             FileOutputStream out = new FileOutputStream(dst)) {
            int c;
            while ((c = in.read()) != -1) {
                //System.out.print(".");
                out.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class CopyBuffered {

    public static void copyFile(String src, String dst) {

        try (
                FileInputStream fis = new FileInputStream(src);
                BufferedInputStream bis = new BufferedInputStream(fis);
                FileOutputStream fos = new FileOutputStream(dst);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            int c;
            while ((c = bis.read()) != -1) {
                bos.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}