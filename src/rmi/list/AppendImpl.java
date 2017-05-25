package rmi.list;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AppendImpl extends UnicastRemoteObject implements Append
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AppendImpl() throws RemoteException
    {
    }

    public void append(ListInterface l) throws RemoteException
    {
        System.out.print("got list: ");
        l.print();
        l.append(4711);
        System.out.print("list manipulated: ");
        l.print();
    }
}