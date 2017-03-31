package uebung1;

import java.io.*;
import java.util.LinkedList;

/**
 * Created by David on 24.03.2017.
 */
public class Aufgabe3 {

    public static void main(String args[]) {


        LinkedList<Byte> bytes = new LinkedList<>();

        try  (FileInputStream is = new FileInputStream("hex.txt")){
            while (true) {
                byte b = (byte) is.read();
                if (b == -1) {
                    break;
                }
                bytes.add(b);
            }

            int count = 0;
            while (!bytes.isEmpty()) {
                System.out.format("%02X : ",count++*16);
                for (int i = 0; i < 16; ++i) {
                    System.out.format("%02X ", bytes.pop());
                    if (bytes.isEmpty())
                        return;
                }
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
