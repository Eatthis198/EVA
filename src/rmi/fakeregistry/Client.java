package rmi.fakeregistry;

import rmi.mult.Multiply;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by David on 25-May-17.
 */
public class Client {

    public Client(int port) throws RemoteException, NotBoundException {
        Registry reg = LocateRegistry.getRegistry(port);

        FakeRegistry fr = (FakeRegistry) reg.lookup("FakeRegistry");

        Multiply multiply = (Multiply) fr.lookUp("Multiply");

        for(int i = 1; i < 10; i++){
            System.out.println(multiply.mult(i,i));
        }

    }

}
