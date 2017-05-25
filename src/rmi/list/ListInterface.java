package rmi.list;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by David on 16.05.2017.
 */
public interface ListInterface extends Remote
{

    public void append(int i) throws RemoteException;

    public void print() throws RemoteException;
    
    public int size() throws RemoteException;
    
    public ListItem getFirstListItem() throws RemoteException;

}
