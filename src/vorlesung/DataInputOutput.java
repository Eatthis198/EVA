package vorlesung;

import java.io.*;

/**
 * Created by David on 31.03.2017.
 */
public class DataInputOutput {

    public static void main(String args[]) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream daos = new DataOutputStream(baos);


        String s = "hallo";

        daos.writeChars(s);

        daos.close();

        byte data[] = baos.toByteArray();
        for (Byte b : data) {
            System.out.format("%02X ",b);
        }

        System.out.println();

        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dais = new DataInputStream(bais);


    }

}
