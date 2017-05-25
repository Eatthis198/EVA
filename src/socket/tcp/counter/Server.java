package socket.tcp.counter;

import socket.tcp.TCPSocket;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by David on 25.04.2017.
 */
public class Server
{

    private static int counter;

    public static void runServer(ServerSocket serverSocket)
    {

        while (true)
        {

            System.out.println("Waiting for client");

            try (TCPSocket tcpSocket = new TCPSocket(serverSocket.accept()))
            {

                while (true)
                {

                    //String request = tcpSocket.receiveLine();
                    String request = tcpSocket.receive();

                    if(request == null || request.isEmpty())
                        request = "null";

                    System.out.println(request);
                    if (request != null)
                    {
                        handleRequest(request);
                    }
//                    else
//                    {
//                        System.out.println("Closing connection!");
//                        break;
//                    }

                    String result = String.valueOf(counter);
                    //tcpSocket.sendLine(result);
                    tcpSocket.send(result+"\n");

                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
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

        if (args.length != 1)
        {
            System.out.println("bitte port angeben");
            return;
        }

        int port = Integer.parseInt(args[0]);

        try (ServerSocket socket = new ServerSocket(port))
        {
            runServer(socket);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
