package uebung3;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * Created by David on 20.04.2017.
 */
public class DatagramDemo {

    public static void main(String args[]) {
        try(DatagramSocket sock = new DatagramSocket()){


            InetAddress address = InetAddress.getByName("google.com");
            int port = 4711;

            //sock.connect(address); // will eine SocketAddress !

            sock.connect(address,port);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
