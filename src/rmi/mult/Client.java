package rmi.mult;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by David on 09.05.2017.
 */
public class Client
{

    public Client(int port)
    {
        try
        {

            Registry registry = LocateRegistry.getRegistry(port);

            Multiply myMultiply = (Multiply) registry.lookup("MultiplyServer");

            for (int i = 1; i < 10; i++)
            {
                for (int j = 1; j < 10; j++)
                {
                    System.out.println(i + " * " + j + " = " + myMultiply.mult(i, j));
                }
            }

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
        new Client(4711);
    }

}
