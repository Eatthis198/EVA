package socket.tcp.relay;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by David on 02.05.2017.
 */
public class TCPRelay {

    private static void runRelay(ServerSocket serverSocket, Socket socket) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 8, 2, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new RelayToServerRunnable(clientSocket, socket));
                pool.execute(new RelayToClientRunnable(clientSocket, socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("RUNCONFIG!");
            return;
        }

        try (
                ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
                Socket socket = new Socket(args[1], Integer.parseInt(args[2]))) {
            runRelay(serverSocket, socket);
        }

    }

}

class RelayToServerRunnable implements Runnable {

    private Socket serverSocket, clientSocket;

    public RelayToServerRunnable(Socket serverSocket, Socket clientSocket) {
        this.serverSocket = serverSocket;
        this.clientSocket = clientSocket;
    }

    public void run() {

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()))) {
            while (clientSocket.isConnected()) {

                // clientSocket.getInputStream().read(buff);
                String tmp = reader.readLine();

                System.out.println("C=>S");

                System.out.println(tmp);

                writer.write(tmp);
                writer.newLine();
                writer.flush();
                // serverSocket.getOutputStream().write(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;

        }

    }

}

class RelayToClientRunnable implements Runnable {

    private Socket serverSocket;

    private Socket clientSocket;

    public RelayToClientRunnable(Socket serverSocket, Socket clientSocket) {
        this.serverSocket = serverSocket;
        this.clientSocket = clientSocket;
    }

    public void run() {

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
            while (clientSocket.isConnected()) {
                // serverSocket.getInputStream().read(buff);
                String tmp = reader.readLine();

                System.out.println("S=>C");

                System.out.println(tmp);

                writer.write(tmp);
                writer.newLine();
                writer.flush();
                // clientSocket.getOutputStream().write(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }

}