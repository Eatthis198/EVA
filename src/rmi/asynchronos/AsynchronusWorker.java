package rmi.asynchronos;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by David on 01-Jun-17.
 */
public interface AsynchronusWorker extends Remote{

    public void work(long time, Callback callback) throws RemoteException;

}
