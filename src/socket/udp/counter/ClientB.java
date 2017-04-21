package socket.udp.counter;

import socket.udp.UDPSocket;

import javax.xml.crypto.Data;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ClientB {
    private static int timeout = 2000; // 2 seconds

    public static void main(String args[]) {
        if (args.length != 3) {
            System.out.println("usage: java " + "socket.udp.Client " + "<server> <port> <count>");
            return;
        }

        final int port = Integer.parseInt(args[1]);

        // create datagram socket
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddr = InetAddress.getByName(args[0]);
            System.out.println("setting counter to 0");

            byte reset[] = new byte[20];
            reset[0] = 0;
            DatagramPacket p = new DatagramPacket(reset, 20);
            p.setAddress(serverAddr);
            p.setPort(port);
            socket.send(p);
            //udpSocket.send("reset", serverAddr, port);
            //String reply = null;
            byte reply[] = new byte[20];
            p.setData(reply);
            try {
                //reply = udpSocket.receive(20);
                socket.receive(p);
                System.out.println("counter = " + Integer.toBinaryString(reply[0]));
            } catch (Exception e) {
                System.out.println(e);
            }
            // get count, initialize start time
            int count = new Integer(args[2]).intValue();
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                System.out.println("incrementing");
                // udpSocket.send("increment", serverAddr, port);
                byte data[] = new byte[20];
                data[0] = 1;
                p.setData(data);
                socket.send(p);

                try {
                    socket.receive(p);//udpSocket.receive(20);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            // display statistics
            long stopTime = System.currentTimeMillis();
            long duration = stopTime - startTime;
            System.out.println("elapsed time = " + duration + " msecs");
            if (count > 0) {
                System.out.println("average ping = " + ((duration) / (float) count) + " msecs");
            }
            System.out.println("counter = " + Integer.toBinaryString(p.getData()[0]));
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("UDP socket closed");
    }
}