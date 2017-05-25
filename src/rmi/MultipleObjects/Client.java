package rmi.MultipleObjects;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by David on 12.05.2017.
 */
public class Client {

    public Client(int port) {
        try {

            Registry registry = LocateRegistry.getRegistry(port);

            String regList[] = registry.list();

            for (String s : regList) {
                System.out.println(s);
            }

            Object fooBar = registry.lookup("FooBar");
            Object fooBar2 = registry.lookup("FooBar");
            Object multiply = registry.lookup("Multiply");

            System.out.println("Same equals: " + String.valueOf(fooBar.equals(fooBar2)));
            System.out.println("Same ==: " + String.valueOf(fooBar == fooBar2));

            System.out.println("Diff equals: " + String.valueOf(fooBar.equals(multiply)));
            System.out.println("Diff ==: " + String.valueOf(fooBar == multiply));


        } catch (RemoteException e) {
            e.printStackTrace();
        }
        catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client(4712);
    }

}
