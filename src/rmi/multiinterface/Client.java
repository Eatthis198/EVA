package rmi.multiinterface;

import rmi.mult.Multiply;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by David on 12.05.2017.
 */
public class Client
{

    public Client(int port)
    {
        try
        {

            Registry registry = LocateRegistry.getRegistry(port);

            Object fooBar = registry.lookup("FooBar");
            System.out.println(((Fooable)fooBar).foo());
            System.out.println(((Barable)fooBar).bar());

        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        catch (NotBoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new Client(4712);
    }

}
