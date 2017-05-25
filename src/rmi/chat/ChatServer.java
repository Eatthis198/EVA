package rmi.chat;

public interface ChatServer extends java.rmi.Remote {
    public boolean addClient(ChatClient objRef) throws java.rmi.RemoteException;

    public void removeClient(ChatClient objRef) throws java.rmi.RemoteException;

    public void sendMessage(String name, String msg) throws java.rmi.RemoteException;
}