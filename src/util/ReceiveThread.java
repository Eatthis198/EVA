package util;

import socket.tcp.TCPSocket;

import java.io.IOException;

public class ReceiveThread extends Thread {

    private TCPSocket socket;

    private int amount;

    public ReceiveThread(TCPSocket socket, int amount) {
        this.socket = socket;
        this.amount = amount;
    }

    public void run() {

        for (int i = 0; i < amount; i++) {
            try {
                String reply = socket.receive();
                System.out.println(reply);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}