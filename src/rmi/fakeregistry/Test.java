package rmi.fakeregistry;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by David on 25-May-17.
 */
public class Test {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        FakeRegistry fr = new FakeRegistryImpl(4711);
        Server server = new Server(4711);
        Client client = new Client(4711);

    }

}
