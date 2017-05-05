package socket.tcp.binaryCounter;

import socket.tcp.TCPSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by David on 25.04.2017.
 */
public class Client {

    public static void main(String args[]) {
        if (args.length != 3) {
            System.out.println("usage: java " + "socket.tcp.counter.Client " + "<server> <count>");
            return;
        }
        // create socket connection
        try (
                Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream())) {

            // set counter to zero
            System.out.println("setting counter to 0");
            //tcpSocket.send("reset");
            dos.writeByte(2);

            int reply = dis.readInt();
            // get count, initialize start time
            System.out.println("incrementing");
            int count = new Integer(args[2]).intValue();
            long startTime = System.currentTimeMillis();
            // perform increment "count" number of times
            for (int i = 0; i < count; i++) {
                //    tcpSocket.sendLine("increment");
                //   reply = tcpSocket.receiveLine();
                //tcpSocket.send("increment");
                dos.writeByte(0);
                // reply = tcpSocket.receive();

            }

            for (int i = 0; i < count; i++) {
                reply = dis.readInt();
            }
            System.out.println(reply);
            // display statistics
            long stopTime = System.currentTimeMillis();
            long duration = stopTime - startTime;
            System.out.println("elapsed time = " + duration + " msecs");
            if (count > 0) {
                System.out.println("average ping = " + ((duration) / (float) count) + " msecs");
            }
            System.out.println("counter = " + reply);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("TCP connection closed");
    }
}