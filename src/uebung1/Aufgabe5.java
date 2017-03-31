package uebung1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * Created by David on 24.03.2017.
 */
public class Aufgabe5 {

    public static void main(String args[]){

        try (FileInputStream fis = new FileInputStream("hex.txt");
            PushbackInputStream pis = new PushbackInputStream(fis,1); ){

            pis.unread(1337);
            pis.unread(2337);

            System.out.println(pis.read() + " | " + pis.read());

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }

}
