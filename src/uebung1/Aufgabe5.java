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

            int b = pis.read();

            System.out.format("%X",b);

            pis.unread(b);
            pis.unread(b);

            System.out.format("%X",pis.read());
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }

}
