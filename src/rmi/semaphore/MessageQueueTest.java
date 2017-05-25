package rmi.semaphore;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by David on 25-May-17.
 */
public class MessageQueueTest {

    public static void main(String[] args) throws RemoteException {
        Server s = new Server();

        new Thread(() -> {
            try {
                new Client(0);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                new Client(10);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

}

class Client{

    public Client(int start) throws RemoteException, NotBoundException, InterruptedException {
        Registry registry = LocateRegistry.getRegistry(4711);

        MessageQueue messageQueue = (MessageQueue)registry.lookup("MessageQueue");

        for (int i = start;  i < start + 10; i++){
            byte arr[] = new byte[1];
            arr[0] = (byte)i;
            messageQueue.push(arr);
        }

        Thread.sleep(5000);

        for(int i = 0; i < 10; i++){
            System.out.println(messageQueue.pop()[0]);
        }
    }

}

class Server{

    public Server() throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(4711);

        MessageQueueImpl messageQueue = new MessageQueueImpl();

        registry.rebind("MessageQueue",messageQueue);

    }

}
