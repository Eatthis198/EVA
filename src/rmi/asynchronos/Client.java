package rmi.asynchronos;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by David on 01-Jun-17.
 */
public class Client {

    public Client(int port) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(port);

        AsynchronusWorker worker = (AsynchronusWorker) registry.lookup("Async");
        worker.work(2000,()-> System.out.println("done!"));
    }

}
