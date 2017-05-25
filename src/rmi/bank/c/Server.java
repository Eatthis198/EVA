package rmi.bank.c;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by David on 16.05.2017.
 */
public class Server
{

    public Server(int port) throws RemoteException
    {

        Registry registry = LocateRegistry.getRegistry(port);

        BankImpl bank = new BankImpl();

        registry.rebind("Bank",bank);

    }

}
