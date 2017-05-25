package rmi.messwerte;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by David on 25-May-17.
 */
public class Test {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Server server = new Server(4711);

        Client c1 = new Client(4711,0,10);
        new Client(4711,20,30);
        new Client(4711,40,50);
        new Client(4711,60,70);
        new Client(4711,80,90);
        c1.registerAdditionalInterval(0,100);


        new MesswertHassendeErfasser("localhost",4712,2000,100);

    }

}
