package rmi.multiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by David on 12.05.2017.
 */
public interface Barable extends Remote {

    public String bar() throws RemoteException;

}
