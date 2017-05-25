package rmi.bank.b;

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

        for (int i = 0; i < 100; i++)
        {
            Account a = new AccountImpl(i);
            registry.rebind("Konto" + i, a);
        }

    }

}
