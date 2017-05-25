package rmi.semaphore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Deque;
import java.util.LinkedList;

public class MessageQueueImpl extends UnicastRemoteObject implements MessageQueue {

    private Deque<byte[]> queue;
    private RMISemaphore mutex, semaphore;

    public MessageQueueImpl() throws RemoteException {
        super();
        queue = new LinkedList<>();
        mutex = new RMISemaphoreImpl(1);
        semaphore = new RMISemaphoreImpl(0);
    }

    @Override
    public void push(byte[] obj) throws RemoteException {
        mutex.acquire();
        try {
            queue.add(obj);
            semaphore.release();
        } finally {
            mutex.release();
        }
    }

    @Override
    public byte[] pop() throws RemoteException {
        mutex.acquire();
        byte result[];
        try {
            semaphore.acquire();
            result = queue.pop();
        } finally {
            mutex.release();
        }
        return result;
    }
}