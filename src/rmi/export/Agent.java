package rmi.export;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by David on 01-Jun-17.
 */
public interface Agent extends Remote {

    public void doJob(String heartbeat) throws RemoteException;

    public String getInfo() throws RemoteException;

    public Agent comeBack() throws RemoteException;

}
