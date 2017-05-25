package rmi.fakeregistry;

import rmi.mult.MultiplyImpl;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by David on 25-May-17.
 */
public class Server {

    public Server(int port) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(port);

        FakeRegistry fr = (FakeRegistry) registry.lookup("FakeRegistry");


        fr.rebind("Multiply",new MultiplyImpl());

    }

}
