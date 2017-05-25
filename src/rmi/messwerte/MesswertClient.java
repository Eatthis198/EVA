package rmi.messwerte;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by David on 25-May-17.
 */
public interface MesswertClient extends Remote {

    public void valueReceived(int value) throws RemoteException;

}
