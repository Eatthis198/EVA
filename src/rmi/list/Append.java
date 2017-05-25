package rmi.list;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Append extends Remote
{
    public void append(ListInterface l) throws RemoteException;
}