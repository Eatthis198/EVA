package uebung1;

import java.io.*;
import java.util.Locale;

/**
 * Created by David on 24.03.2017.
 */
public class Aufgabe2 {

    public static void main(String args[]) {

        try (FileOutputStream outputStream = new FileOutputStream("printStreamTest");
             PrintStream printStream = new PrintStream(System.out, true);
        ){

            float f = 123.123f;

            printStream.format(Locale.US, "Das ist %f ",f);
            printStream.format(Locale.GERMAN, "Das ist %f ",f);

            System.out.println();

            short s = 16;
            printStream.format("%02X",s);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
