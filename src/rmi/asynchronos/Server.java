package rmi.asynchronos;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.*;

/**
 * Created by David on 01-Jun-17.
 */
public class Server extends UnicastRemoteObject implements AsynchronusWorker {

    public Server(int port) throws RemoteException {
        super();
        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind("Async",this);
    }

    @Override
    public void work(long time, Callback callback) throws RemoteException {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new WorkerThread(r,callback).start();
    }
}

class WorkerThread extends Thread{

    private Runnable runnable;
    private Callback callback;

    public WorkerThread(Runnable r, Callback c){
        runnable = r;
        callback = c;
    }

    public void run(){
        runnable.run();
        callback.callBack();
    }



}