package socket.tcp;

import java.io.*;
import java.net.*;

public class TCPSocket implements AutoCloseable {
    private Socket socket;

    private BufferedReader istream;

    private BufferedWriter ostream;

    public TCPSocket(String serverAddress, int serverPort) throws UnknownHostException, IOException {
        socket = new Socket(serverAddress, serverPort);
        initializeStreams();
    }

    public TCPSocket(Socket socket) throws IOException {
        this.socket = socket;
        initializeStreams();
    }

    public void sendLine(String s) throws IOException {
        ostream.write(s);
        ostream.newLine();
        ostream.flush();
    }

    public void send(String s) throws IOException {
        if (s == null || s.isEmpty())
            return;
        ostream.write(s);
        ostream.flush();
    }

    public String receive() throws IOException {
        char data[] = new char[socket.getReceiveBufferSize()];

        //System.out.println("ReceiveBuffer: " + String.valueOf(socket.getReceiveBufferSize()));

        int index = 0;
        int in;

        while (/*istream.ready() && */(in = istream.read()) != '\n') {
            data[index++] = (char) in;
        }

        return index == 0 ? null : new String(data,0,index-1);

//        return index == 0 ? "" : new String(data);
    }

    public String receiveLine() throws IOException {
        return istream.readLine();
    }

    public void close() throws IOException {
        socket.close();
    }

    private void initializeStreams() throws IOException {
        ostream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        istream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public Socket getSocket() {
        return socket;
    }

}