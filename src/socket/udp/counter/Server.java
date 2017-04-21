package socket.udp.counter;

import socket.udp.UDPSocket;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server
{

    private static int counter = 0;

    public static void runServer(DatagramSocket socket)
    {
        try (UDPSocket udpSocket = new UDPSocket(socket))
        {

            // wait for request packets
            System.out.println("waiting for client requests");
            while (true)
            {
                try
                {
                    // receive request
                    String request = udpSocket.receive(20);

                    handleRequest(request);

                    // generate answer
                    String answer = String.valueOf(counter);
                    // send answer
                    udpSocket.reply(answer);
                }
                catch (IOException e)
                {

                }
            }
        }
    }

    private static void handleRequest(String request)
    {

        StringBuffer buff = new StringBuffer(request);
        int spaceIndex = buff.lastIndexOf(" ");

        if (spaceIndex != -1 && request.startsWith("set"))
        {
            try
            {
                int newValue = Integer.parseInt(buff.substring(spaceIndex + 1));
                counter = newValue;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Failed to parse int");
            }
            return;
        }

        switch (request)
        {
            case "increment":
                counter++;
                break;
            case "decrement":
                counter--;
                break;
            case "reset":
                counter = 0;
                break;
            default:
                break;
        }
        System.out.println(request + " counter is now: " + counter);
    }

    public static void main(String args[])
    {
        final int port = Integer.parseInt(args[0]);

        try (DatagramSocket sock = new DatagramSocket(port))
        {
            runServer(sock);
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
    }
}