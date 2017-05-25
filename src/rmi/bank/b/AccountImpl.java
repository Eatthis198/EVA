package rmi.bank.b;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by David on 16.05.2017.
 */
public class AccountImpl extends UnicastRemoteObject implements Account
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private double balance;

    public AccountImpl(double startBalance) throws RemoteException
    {
        super();
        balance = startBalance;
    }

    @Override
    public double readBalance() throws RemoteException
    {
        return balance;
    }

    @Override
    public void changeBalance(double v) throws RemoteException, OverdrawAccountException
    {
        balance += v;
        if (balance < 0)
        {
            balance -= v;
            throw new OverdrawAccountException();
        }
    }
}
