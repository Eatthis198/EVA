package vorlesung;

import java.io.*;

/**
 * Created by David on 31.03.2017.
 */
public class PrintExample {

    public static void main(String args[]) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);

        ps.print(true);
        ps.print(false);
        ps.print(256);
        ps.print(-1);

        byte data[] = baos.toByteArray();
        for (Byte b : data) {
            System.out.format("%02X ",b);
        }


    }

}
