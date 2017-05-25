package rmi.semaphore;

import java.rmi.RemoteException;

/**
 * Created by David on 25-May-17.
 */
public interface RMISemaphore {


    public void acquire() throws RemoteException;

    public void release() throws RemoteException;

}
