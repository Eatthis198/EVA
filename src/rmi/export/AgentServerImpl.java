package rmi.export;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by David on 01-Jun-17.
 */
public class AgentServerImpl extends UnicastRemoteObject implements AgentServer {

    Agent agent;
    int port;

    public AgentServerImpl(int port) throws RemoteException {
        super();

        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind("AgentServer", this);
        this.port = port;
    }

    @Override
    public Agent takeAgent(Agent agent) throws RemoteException {
        this.agent = agent;
        return (Agent) UnicastRemoteObject.exportObject(this.agent,port);
    }
}




