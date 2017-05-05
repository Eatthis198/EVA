package socket.tcp.binaryCounter;

import socket.tcp.TCPSocket;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by David on 25.04.2017.
 */
public class Server {

    private static int counter;

    public static void runServer(ServerSocket serverSocket) {

        while (true) {

            System.out.println("Waiting for client");

            try (Socket socket = serverSocket.accept();
                 DataInputStream dis = new DataInputStream(socket.getInputStream());
                 DataOutputStream dos = new DataOutputStream(socket.getOutputStream())
            ) {

                while (true) {

                    byte request = dis.readByte();
                    //String request = tcpSocket.receive();

                    System.out.println(request);
                    handleRequest(request, dis);
//                    else
//                    {
//                        System.out.println("Closing connection!");
//                        break;
//                    }

//                    String result = String.valueOf(counter);
                    dos.writeInt(counter);
                    //tcpSocket.send(result);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private static void handleRequest(byte request, DataInputStream dis) {

        if (request == 3) {
            try {
                int newValue = dis.readInt();
                counter = newValue;
            } catch (NumberFormatException e) {
                System.out.println("Failed to parse int");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        switch (request) {
            case 0:
                counter++;
                break;
            case 1:
                counter--;
                break;
            case 2:
                counter = 0;
                break;
            default:
                break;
        }
        System.out.println(request + " counter is now: " + counter);
    }

    public static void main(String args[]) {

        if (args.length != 1) {
            System.out.println("bitte port angeben");
            return;
        }

        int port = Integer.parseInt(args[0]);

        try (ServerSocket socket = new ServerSocket(port)) {
            runServer(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
