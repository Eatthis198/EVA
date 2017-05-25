package rmi.messwerte;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by David on 25-May-17.
 */
public interface MesswertServer extends Remote {

    public void registerClient(MesswertClient client, int start, int end) throws RemoteException;

}
