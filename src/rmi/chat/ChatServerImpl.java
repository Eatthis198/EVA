package rmi.chat;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
    private ArrayList<ChatClient> allClients;

    private List<String> lastMessages;

    private final int savedMessageCount = 10;

    public ChatServerImpl() throws RemoteException {
        allClients = new ArrayList<ChatClient>();

        lastMessages = new LinkedList<>();
    }

    public synchronized boolean addClient(ChatClient objRef) throws RemoteException {
        String name = objRef.getName();
        for (Iterator<ChatClient> iter = allClients.iterator(); iter.hasNext(); ) {
            ChatClient cc = iter.next();
            try {
                if (cc.getName().equals(name)) {
                    return false;
                }
            } catch (RemoteException exc) {
                iter.remove();
            }
        }

        allClients.add(objRef);

        for(ChatClient c : allClients){
            c.print("Server: " + objRef.getName() + " has connected!");
        }

        for(String s: lastMessages){
            objRef.print(s);
        }

        return true;
    }

    public synchronized void removeClient(ChatClient objRef)
            throws RemoteException {
        String name = objRef.getName();
        allClients.remove(objRef);

        for(ChatClient c : allClients){
            c.print("Server: " + objRef.getName() + " has disconnected!");
        }

    }

    public synchronized void sendMessage(String name, String msg)
            throws RemoteException {
        for (Iterator<ChatClient> iter = allClients.iterator();
             iter.hasNext(); ) {
            ChatClient cc = iter.next();
            try {
                cc.print(name + ": " + msg);
            } catch (RemoteException exc) {
                iter.remove();
            }
        }
        addMessageToList(name + ": " +msg);
    }

    private void addMessageToList(String message){
        lastMessages.add(message);
        if(lastMessages.size() > savedMessageCount){
            lastMessages.remove(0);
        }
    }
}