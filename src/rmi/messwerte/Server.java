package rmi.messwerte;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by David on 25-May-17.
 */
public class Server extends UnicastRemoteObject implements MesswertServer {

    private List<ClientInterval> clients;

    public Server(int port) throws RemoteException {
        super();
        clients = new LinkedList<>();


        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind("MesswertServer",this);
        new ReceiverThread(port+1,this);
        System.out.println("Server Created!");
    }

    @Override
    public void registerClient(MesswertClient client, int start, int end) throws RemoteException {
        ClientInterval ci = new ClientInterval(client, start, end);
        if(!clients.contains(ci)){
            clients.add(ci);
            System.out.println("Client:" + start + " - " + end + " registered!");
        }
    }

    public void valueReceived(int value) throws RemoteException {
        for(ClientInterval ci: clients){
            if(ci.isInInterval(value)){
                ci.sendValue(value);
            }
        }
    }
}

class ReceiverThread extends Thread {

    private int port;
    private Server server;

    public ReceiverThread(int port, Server server) {
        this.port = port;
        this.server = server;
        this.start();
    }

    public void run() {
        while (!isInterrupted()) {
            byte buffer[] = new byte[4];
            try (DatagramSocket socket = new DatagramSocket(port);
                 ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
                 DataInputStream dis = new DataInputStream(bis);) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                server.valueReceived(dis.readInt());
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ClientInterval {

    private MesswertClient client;
    private int start, end;

    public ClientInterval(MesswertClient client, int rangeStart, int rangeEnd) {
        this.client = client;
        this.start = rangeStart;
        this.end = rangeEnd;
    }

    public boolean isInInterval(int value) {
        if (value < start || value >= end) return false;
        return true;
    }

    public void sendValue(int value) throws RemoteException {
        client.valueReceived(value);
    }
}