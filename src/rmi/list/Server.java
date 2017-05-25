package rmi.list;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server
{

    public Server(int port)
    {
        try
        {
            Registry registry = LocateRegistry.createRegistry(port);

            Append server = new AppendImpl();
            AppendCopy serverCopy = new AppendImplCopy();

            registry.rebind("AppendServer", server);
            registry.rebind("AppendServerCopy",serverCopy);
        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
         Server s = new Server(4711);
    }
}