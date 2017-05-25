package rmi.MultipleObjects;

/**
 * Created by David on 12.05.2017.
 */

import rmi.mult.MultiplyImpl;
import rmi.multiinterface.FooBar;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 * Created by David on 12.05.2017.
 */
public class Server
{

    public Server(int port)
    {
        try
        {
            FooBar fooBar = new FooBar();
            MultiplyImpl multiply = new MultiplyImpl();
            //Registry registry = LocateRegistry.getRegistry(port);
            Registry registry = LocateRegistry.createRegistry(port);

            registry.rebind("FooBar", fooBar);
            registry.rebind("Multiply",multiply);
            System.out.println("server is ready");
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new Server(4712);
    }
}

