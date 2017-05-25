package rmi.threadNames;

import rmi.mult.MultiplyImpl;
import rmi.multiinterface.FooBar;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by David on 12.05.2017.
 */
public class Server {

    public Server(int port) {
        try {
            ThreadNamerImpl threadNamer = new ThreadNamerImpl();
            //MyRegistry registry = LocateRegistry.getRegistry(port);
            Registry registry = LocateRegistry.createRegistry(port);

            registry.rebind("ThreadNamer",threadNamer);
            System.out.println("server is ready");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server(4712);
    }
}