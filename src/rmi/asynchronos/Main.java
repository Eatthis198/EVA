package rmi.asynchronos;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by David on 01-Jun-17.
 */
public class Main {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Server server = new Server(4711);
        Client client = new Client(4711);
    }

}
