package rmi.mult;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by David on 09.05.2017.
 */
public interface Multiply extends Remote
{

    public int mult(int a, int b) throws RemoteException;

}
