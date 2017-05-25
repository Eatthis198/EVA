package rmi.threadNames;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by David on 12.05.2017.
 */
public interface ThreadNamer extends Remote {

    public String getCurrentThreadName() throws RemoteException;
}
