package socket.udp.counter;

import socket.udp.UDPSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by David on 10.04.2017.
 */
public class InteractiveClient
{

    private static int timeout = 2000; // 2 seconds

    public static void main(String args[])
    {
        if (args.length != 3)
        {
            System.out.println("usage: java " + "socket.udp.Client " + "<server> <port> <count>");
            return;
        }

        final int port = Integer.parseInt(args[1]);

        // create datagram socket
        try (UDPSocket udpSocket = new UDPSocket();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
        {
            udpSocket.setTimeout(timeout);
            // get inet addr of server
            InetAddress serverAddr = InetAddress.getByName(args[0]);

            while (true)
            {
                String input = reader.readLine();
                if (input.equals("exit"))
                {
                    break;
                }
                udpSocket.send(input, serverAddr, port);
                try
                {
                    String reply = udpSocket.receive(20);
                    System.out.println("Counter = " + reply);
                }
                catch (Exception e)
                {

                }
            }

            System.out.println("UDP socket closed");
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
