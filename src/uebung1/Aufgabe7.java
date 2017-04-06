package uebung1;

import java.io.*;

/**
 * Created by David on 24.03.2017.
 */
public class Aufgabe7 {

    public static void main(String args[]) {

        try (
                Aufgabe6 a = new Aufgabe6(System.out, new FileOutputStream("dos.txt"));
                DataOutputStream dos = new DataOutputStream(a);
                DataInputStream dis = new DataInputStream(new FileInputStream("dos.txt"));
        ) {


            byte bytes[] = {'a', 'b', 'c'};
            dos.write(bytes);
            dos.writeBoolean(true);
            dos.writeShort(1);
            dos.writeInt(2);
            dos.writeLong(3);
            dos.writeFloat(4.0f);
            dos.writeDouble(5.0f);
            dos.writeBytes("HalloBytes");
            dos.writeChars("HalloChars");
            dos.writeUTF("HalloUTF");

            System.out.println();
            System.out.println(bytes);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
