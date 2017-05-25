package rmi.list;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;

public class Client
{

    public Client(int port)
    {
        try
        {

            Registry registry = LocateRegistry.getRegistry(port);
            Append server = (Append) registry.lookup("AppendServer");

            System.out.println("Server contacted");
            List l = new List();
            registry.rebind("List", l);

            for (int i = 0; i < 5; i++)
            {
                int value = i;
                l.append(value);
            }
            System.out.print("list before RMI: ");
            l.print();
            server.append(l);
            System.out.print("list after RMI: ");
            l.print();

            AppendCopy serverCopy = (AppendCopy) registry.lookup("AppendServerCopy");

            System.out.println("Server contacted");
            ListCopy l2 = new ListCopy();

            for (int i = 0; i < 99999; i++)
            {
                int value = i;
                l2.append(value);
            }
            System.out.print("list before RMI: ");
            l2.print();
            l2 = serverCopy.append(l2);
            System.out.print("list after RMI: ");
            l2.print();


        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        if (args.length < 2)
        {
            System.out.println("usage: java rmi.list.Client" + " <server>" + " <val1> <val2> ..." + " <valN>");
            return;
        }
        Client c = new Client(4711);
    }

}