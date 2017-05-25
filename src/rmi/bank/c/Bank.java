package rmi.bank.c;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by David on 16.05.2017.
 */
public interface Bank extends Remote
{

    public Account getAccount(int i) throws RemoteException;

}
