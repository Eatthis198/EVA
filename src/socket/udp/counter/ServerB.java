package socket.udp.counter;

import socket.udp.UDPSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerB {

    private static int counter = 0;

    public static void runServer(DatagramSocket socket) {

        // wait for request packets
        System.out.println("waiting for client requests");
        while (true) {
            try {
                // receive request
                //String request = udpSocket.receive(20);
                byte data[] = new byte[20];
                DatagramPacket p = new DatagramPacket(data, 20);
                socket.receive(p);


                handleRequest(p.getData());

                // generate answer
                String answer = String.valueOf(counter);
                data[0] = (byte) counter;
                p.setData(data);
                // send answer
                //udpSocket.reply(answer);
                socket.send(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private static void handleRequest(byte request[]) {


        switch (request[0]) {
            case 0:
                counter = 0;
                break;
            case 1:
                counter++;
                break;
            case 2:
                counter--;
                break;
            case 3:
                counter = request[1];
                break;
            default:
                break;
        }
        System.out.println("Recieved: " + Integer.toBinaryString(request[0]) + " counter is now: " + counter);
    }

    public static void main(String args[]) {
        final int port = Integer.parseInt(args[0]);

        try (DatagramSocket sock = new DatagramSocket(port)) {
            runServer(sock);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}