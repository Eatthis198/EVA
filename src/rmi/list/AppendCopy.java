package rmi.list;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AppendCopy extends Remote
{
    public ListCopy append(ListCopy l) throws RemoteException;
}