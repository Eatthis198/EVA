package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by David on 04.04.2017.
 */
public class HexReader {

    private static HexReader instance;

    private HexReader(){}

    public static HexReader getInstance(){
        if(instance == null){
            instance = new HexReader();
        }
        return instance;
    }

    public void readFile(String file){
        LinkedList<Byte> bytes = new LinkedList<>();

        try  (FileInputStream is = new FileInputStream(file)){
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
