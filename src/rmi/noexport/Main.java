package rmi.noexport;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by David on 01-Jun-17.
 */
public class Main {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Server server = new Server(4711);
        Client client = new Client(4711);
    }
}

interface Echo extends Remote {
    public void echo(String s) throws RemoteException;
}

class Server implements Echo,Serializable {

    public Server(int port) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind("EchoServer", this);
    }

    @Override
    public void echo(String s) throws RemoteException {
        System.out.println(s);
    }
}

class Client{

    public Client(int port) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(port);

        Server echo = (Server) registry.lookup("EchoServer");
        echo.echo("heeeYo");
    }

}