package rmi.bank.b;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by David on 16.05.2017.
 */
public interface Account extends Remote
{

    public double readBalance() throws RemoteException;

    public void changeBalance(double v) throws RemoteException, OverdrawAccountException;

}
