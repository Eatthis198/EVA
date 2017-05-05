package socket.tcp.binarytree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by David on 02.05.2017.
 */

public class TcpTreeServer
{

    public static void runServer(ServerSocket serverSocket)
    {

        while (true)
        {

            System.out.println("Waiting for client");

            try (Socket socket = serverSocket.accept())
            {

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                Object obj = ois.readObject();

                BinaryTree tree = (BinaryTree) obj;

                tree.printLevelOrder(tree.getRoot());

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }

        }

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
