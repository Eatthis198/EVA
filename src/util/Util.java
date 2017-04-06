package util;

import java.io.*;
import java.util.LinkedList;

/**
 * Created by David on 04.04.2017.
 */
public class Util {

    private static Object deepCopy(Object src) {

        Object dst = null;

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos);) {

            oos.writeObject(src);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);

            dst = ois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dst;
    }

    public static <T extends Serializable> T genericCopy(T src) throws IOException, ClassNotFoundException {

        T dst = null;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(src);
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);

        dst = (T) ois.readObject();

        return dst;


    }


    public static void readFile(String file) {
        LinkedList<Byte> bytes = new LinkedList<>();

        try (FileInputStream is = new FileInputStream(file)) {
            while (true) {
                byte b = (byte) is.read();
                if (b == -1) {
                    break;
                }
                bytes.add(b);
            }

            int count = 0;
            while (!bytes.isEmpty()) {
                System.out.format("%02X : ", count++ * 16);
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
