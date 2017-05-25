package vorlesung;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by David on 31.03.2017.
 */
public class ZipExample {

    public static void main(String args[]) throws IOException {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream zos = new GZIPOutputStream(baos);

        byte bytes[] = new byte[100000];
        for(int i = 0; i < bytes.length; ++i){
            bytes[i] = 0;
        }

        zos.write(bytes);
        zos.close();


        byte data[] = baos.toByteArray();
        System.out.println("Original length: " + bytes.length);
        System.out.println("Zipped length: " + data.length);

        for(byte b : data){
            System.out.format("%02X ", b);
        }

    }

}
