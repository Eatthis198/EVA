package socket.udp.counter;

import java.net.*;

import socket.udp.UDPSocket;

public class Client
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
        try (UDPSocket udpSocket = new UDPSocket())
        {
            udpSocket.setTimeout(timeout);
            // get inet addr of server
            InetAddress serverAddr = InetAddress.getByName(args[0]);
            // set counter to zero
            System.out.println("setting counter to 0");
            udpSocket.send("reset", serverAddr, port);
            String reply = null;
            // receive reply
            try
            {
                reply = udpSocket.receive(20);
                System.out.println("counter = " + reply);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            // get count, initialize start time
            System.out.println("incrementing");
            int count = new Integer(args[2]).intValue();
            long startTime = System.currentTimeMillis();
            // perform increment "count" number of times
            for (int i = 0; i < count; i++)
            {
                udpSocket.send("increment", serverAddr, port);
                try
                {
                    reply = udpSocket.receive(20);
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
            // display statistics
            long stopTime = System.currentTimeMillis();
            long duration = stopTime - startTime;
            System.out.println("elapsed time = " + duration + " msecs");
            if (count > 0)
            {
                System.out.println("average ping = " + ((duration) / (float) count) + " msecs");
            }
            System.out.println("counter = " + reply);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("UDP socket closed");
    }
}