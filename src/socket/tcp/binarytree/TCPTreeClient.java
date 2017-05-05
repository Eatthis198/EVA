package socket.tcp.binarytree;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by David on 02.05.2017.
 */
public class TCPTreeClient
{

    public static void main(String args[])
    {
        if (args.length != 3)
        {
            System.out.println("usage: java " + "socket.tcp.counter.Client " + "<server> <count>");
            return;
        }
        // create socket connection
        try (Socket socket = new Socket(args[0], Integer.parseInt(args[1])))
        {

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            // BAUM
            BinaryTree t = new BinaryTree(2);
            t.append(1);
            t.append(3);
            t.append(0);
            oos.writeObject(t);

            t.printLevelOrder(t.getRoot());

        }
        catch (Exception e)
        {
            System.out.println("catch");
            e.printStackTrace();
        }
        System.out.println("TCP connection closed");
    }
}
