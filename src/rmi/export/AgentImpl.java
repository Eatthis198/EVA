package rmi.export;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by David on 01-Jun-17.
 */
public class AgentImpl implements Agent, Serializable {

    private AgentThread thread;
    private String info;

    public AgentImpl() throws RemoteException {
    }

    @Override
    public void doJob(String heartbeat) throws RemoteException {
        thread = new AgentThread(heartbeat);
        thread.start();
    }

    @Override
    public String getInfo() throws RemoteException {
        return "Heyyyyoooo";
    }

    @Override
    public Agent comeBack() throws RemoteException {
        thread.interrupt();
        UnicastRemoteObject.unexportObject(this, true);
        return this;
    }
}

class AgentThread extends Thread {

    private String message;

    public AgentThread(String message) {
        this.message = message;
        System.out.println("Thread started");
    }

    public void run() {

        while (!isInterrupted()) {
            try {
                System.out.println(message);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
