package rmi.multiinterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by David on 12.05.2017.
 */
public class FooBar extends UnicastRemoteObject implements Fooable,Barable {

    public FooBar() throws RemoteException {
        super();
    }

    @Override
    public String bar() throws RemoteException {
        return "Bar";
    }

    @Override
    public String foo() throws RemoteException {
        return "foo";
    }
}
