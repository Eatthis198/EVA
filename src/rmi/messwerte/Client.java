package rmi.messwerte;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by David on 25-May-17.
 */
public class Client extends UnicastRemoteObject implements MesswertClient {

    private int start, end;

    public Client(int port, int start, int end) throws RemoteException, NotBoundException {
        super();

        this.start = start;
        this.end = end;

        Registry registry = LocateRegistry.getRegistry(port);
        MesswertServer server = (MesswertServer) registry.lookup("MesswertServer");

        server.registerClient(this, start, end);
    }

    @Override
    public void valueReceived(int value) throws RemoteException {
        System.out.println("Client " + start + "- " + end +" received: " + value);
    }
}
