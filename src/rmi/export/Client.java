package rmi.export;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by David on 01-Jun-17.
 */
public class Client {

    public Client(int port) throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry(port);

        AgentServer server = (AgentServer) registry.lookup("AgentServer");

        Agent agent = new AgentImpl();
        Agent remote = server.takeAgent(agent);

        remote.doJob("aHa");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        remote.comeBack();
    }

}
