package rmi.bank.c;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 16.05.2017.
 */
public class BankImpl extends UnicastRemoteObject implements Bank
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private List<Account> accounts;

    public BankImpl() throws RemoteException
    {
        super();
        accounts = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            accounts.add(new AccountImpl(0));
        }
    }

    @Override
    public Account getAccount(int i) throws RemoteException
    {
        if (i < 0 || i >= accounts.size())
        {
            throw new IllegalArgumentException();
        }

        return accounts.get(i);
    }
}
