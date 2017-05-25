package rmi.list;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AppendImplCopy extends UnicastRemoteObject implements AppendCopy {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AppendImplCopy() throws RemoteException {
    }

    public ListCopy append(ListCopy l) throws RemoteException {
        System.out.print("got list: ");
        l.print();
        l.append(4711);
        System.out.print("list manipulated: ");
        l.print();
        return l;
    }
}