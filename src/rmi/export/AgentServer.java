package rmi.export;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by David on 01-Jun-17.
 */
public interface AgentServer extends Remote{

    public Agent takeAgent(Agent agent) throws RemoteException;

}
