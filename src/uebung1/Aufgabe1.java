package uebung1;

import java.io.*;

/**
 * Created by David on 24.03.2017.
 */
public class Aufgabe1 {

    public static void main(String args[]) {

        String fileName = args[0];
        int fileSize = 0;
        try {
            fileSize = Integer.parseInt(args[1]);
            fileSize = Math.abs(fileSize);
        } catch(NumberFormatException e){
            System.out.println(args[1] + " is not an Integer value");
            return;
        }


        try (FileOutputStream outputStream = new FileOutputStream(fileName);){

            byte contents[] = new byte[fileSize];
            outputStream.write(contents);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Filename: "  + fileName + ", Filesize: " + fileSize);

    }

}
