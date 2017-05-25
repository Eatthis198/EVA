package rmi.multiinterface;

import rmi.mult.Multiply;
import rmi.mult.MultiplyImpl;

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

            //MyRegistry registry = LocateRegistry.getRegistry(port);
            Registry registry = LocateRegistry.createRegistry(port);

            registry.rebind("FooBar", fooBar);
            System.out.println("FooBar server is ready");
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

