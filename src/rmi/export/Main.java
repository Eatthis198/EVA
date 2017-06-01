package rmi.export;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by David on 01-Jun-17.
 */
public class Main {

    public static void main(String[] args) {
        try {
            int port = 4711;
            AgentServer server = new AgentServerImpl(port);
            Client client = new Client(port);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}
