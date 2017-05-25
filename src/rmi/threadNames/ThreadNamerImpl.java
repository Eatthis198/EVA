package rmi.threadNames;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by David on 12.05.2017.
 */
public class ThreadNamerImpl extends UnicastRemoteObject implements ThreadNamer{

    public ThreadNamerImpl() throws RemoteException {
        super();
    }

    @Override
    public String getCurrentThreadName() throws RemoteException {
        return Thread.currentThread().getName();
    }
}
