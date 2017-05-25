package rmi.bank.c;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by David on 16.05.2017.
 */
public class Client
{

    public Client(int port, String args[]) throws RemoteException, OverdrawAccountException
    {

        try
        {

            int accountId = Integer.parseInt(args[1]);

            double change;
            change = args.length == 3 ? Double.parseDouble(args[2]) : 0.0;

            Registry registry = LocateRegistry.getRegistry(port);

            if (accountId < 0 || accountId >= 100)
            {
                throw new IllegalArgumentException();
            }

            Bank bank = (Bank) registry.lookup("Bank");
            Account account = bank.getAccount(accountId);

            switch (args[0])
            {
                case "l":
                    System.out.println(account.readBalance());
                    break;
                case "s":
                    account.changeBalance(change);
                    System.out.println(account.readBalance());
                    break;
                default:
                    break;
            }

        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException();
        }
        catch (NotBoundException e)
        {
            e.printStackTrace();
        }

    }

}
