package rmi.threadNames;

import javax.net.ssl.SSLSocket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by David on 12.05.2017.
 */
public class Client {

    private ThreadNamer threadNamer;

    public Client(int port) {
        try {

            Registry registry = LocateRegistry.getRegistry(port);

            threadNamer = (ThreadNamer) registry.lookup("ThreadNamer");

            System.out.println(threadNamer.getCurrentThreadName());
//            for(int i = 0; i < 10; i++){
//                System.out.println(threadNamer.getCurrentThreadName());
//            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        Client c = new Client(4712);
//        c.multiThread(c.threadNamer,10);
        multiClients(10);
    }

    private static void multiClients(int amount) {
        for (int i = 0; i < amount; i++) {
            new Thread(() -> new Client(4712)).start();
        }
    }

    private void multiThread(ThreadNamer tn, int amount) {

        Client c = new Client(4712);

        for (int i = 0; i < amount; i++) {
            new Thread(() -> {
                try {
                    System.out.println(tn.getCurrentThreadName());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

}