package rmi.list;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class List extends UnicastRemoteObject implements Serializable, ListInterface
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ListItem first, last;

    public List() throws RemoteException
    {
        super();
    }

    public void append(int i) throws RemoteException
    {
        if (first == null)
        {
            first = new ListItem(i);
            last = first;
        }
        else
        {
            last.setNext(new ListItem(i));
            last = last.getNext();
        }
    }

    public void print() throws RemoteException
    {
        ListItem item = first;
        while (item != null)
        {
            System.out.print(item.getValue() + " ");
            item = item.getNext();
        }
        System.out.println();
    }

    public int size()
    {
        int size = 0;
        ListItem current = first;
        while (current != null)
        {
            ++size;
            current = current.getNext();
        }
        return size;
    }

    public ListItem getFirstListItem()
    {
        return first;
    }

}