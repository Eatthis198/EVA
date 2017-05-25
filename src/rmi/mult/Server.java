package rmi.mult;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * Created by David on 09.05.2017.
 */
public class Server
{

    public Server(int port)
    {
        try
        {
            MultiplyImpl myMultiply = new MultiplyImpl();
            Registry registry = LocateRegistry.getRegistry(port);
            //MyRegistry registry = LocateRegistry.createRegistry(port);
            
            registry.rebind("MultiplyServer", myMultiply);
            System.out.println("multiply server is ready");
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }
   
    public static void main(String[] args)
    {
        new Server(4711);
    }
}
