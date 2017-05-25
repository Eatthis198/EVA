package rmi.semaphore;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by David on 25-May-17.
 */
public interface MessageQueue extends Remote {

    public void push(byte[] obj) throws RemoteException;

    public byte[] pop() throws RemoteException;

}
