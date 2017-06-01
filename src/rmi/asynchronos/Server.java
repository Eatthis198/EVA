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

    private ThreadPoolExecutor poolExecutor;

    public Server(int port) throws RemoteException {
        super();

        poolExecutor = new ThreadPoolExecutor(4,8,2000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind("Async",this);
    }

    @Override
    public void work(long time, Callback callback) throws RemoteException {
        Future<?> future = poolExecutor.submit(()->seep(time));
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        callback.callBack();
    }

    private void seep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
