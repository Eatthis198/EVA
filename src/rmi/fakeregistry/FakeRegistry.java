package rmi.fakeregistry;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by David on 25-May-17.
 */
public interface FakeRegistry extends Remote {

    public void rebind(String name,Remote obj) throws RemoteException;

    public Remote lookUp(String name) throws RemoteException;

}
