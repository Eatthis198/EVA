package util;

import socket.tcp.TCPSocket;

import java.io.IOException;

public class SendThread extends Thread {

    private TCPSocket socket;

    private int amount;

    public SendThread(TCPSocket socket, int amount) {
        this.socket = socket;
        this.amount = amount;
    }

    public void run() {

        for (int i = 0; i < amount; i++) {
            try {
                socket.send("increment");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
