package rmi.mult;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by David on 09.05.2017.
 */
public class MultiplyImpl extends UnicastRemoteObject implements Multiply
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MultiplyImpl() throws RemoteException
    {
        super();
    }

    @Override
    public synchronized int mult(int a, int b) throws RemoteException
    {
        return a * b;
    }
}
