package uebung2;


import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;
import util.MultiOutputStream;

import java.io.*;

/**
 * Created by David on 06.04.2017.
 */
public class XStreamTest {

    private String name;

    public XStreamTest(String name) {
        this.name = name;
    }

    public static void main(String args[]) {

        String fileName = "XStream.xml";

        XStream xStream = new XStream(new DomDriver());
        xStream.alias("Alpha",A.class);
        xStream.alias("Bravo",B.class);
        xStream.alias("Charlie",C.class);


        A a = new A();
        a.setI(1);
        B b = new B();
        b.setI(2);
        C c = new C();
        c.setI(3);
        c.setA(a);


        a.setB(b);
        b.setC(c);

        String aXml = xStream.toXML(a);
        b.setI(10);
        String bXml = xStream.toXML(b);
        String cXml = xStream.toXML(c);

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             FileOutputStream fos = new FileOutputStream(fileName);
             MultiOutputStream mos = new MultiOutputStream(fos,bos,System.out);
             DataOutputStream dos = new DataOutputStream(mos)) {
            dos.writeUTF(aXml);
            dos.writeUTF(bXml);
            dos.writeUTF(cXml);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            DataInputStream dis = new DataInputStream(bis);

            A a2 = (A) xStream.fromXML(dis.readUTF());
            B b2 = (B) xStream.fromXML(dis.readUTF());
            C c2 = (C) xStream.fromXML(dis.readUTF());

            System.out.println("Ende");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

class A {
    private B b;

    private int i;

    public void setB(B b) {
        this.b = b;
    }

    public void setI(int x) {
        i = x;
    }

}

class B {
    private C c;

    private int i;

    public void setC(C c) {
        this.c = c;
    }

    public void setI(int x) {
        i = x;
    }
}

class C {
    private A a;

    private int i;

    public void setA(A a) {
        this.a = a;
    }

    public void setI(int x) {
        i = x;
    }
}