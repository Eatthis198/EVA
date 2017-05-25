package rmi.semaphore;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class RMISemaphoreImpl extends UnicastRemoteObject
        implements RMISemaphore {
    private int value;

    public RMISemaphoreImpl(int init) throws RemoteException {
        if (init < 0)
            init = 0;
        value = init;
    }

    public synchronized void acquire() throws RemoteException {
        while (value == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        value--;
    }

    public synchronized void release() throws RemoteException {
        value++;
        notify();
    }
}