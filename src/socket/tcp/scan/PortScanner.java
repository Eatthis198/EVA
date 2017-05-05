package socket.tcp.scan;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by David on 02.05.2017.
 */
public class PortScanner
{

    public static void main(String[] args)
    {

        if (args.length != 3)
        {
            System.out.println("RUNCONFIG");
            return;
        }

        int lowestPort = Integer.parseInt(args[1]);
        int highestPort = Integer.parseInt(args[2]);

        List<Integer> availablePorts = new LinkedList<>();

        for (int i = lowestPort; i <= highestPort; i++)
        {

            try (Socket sock = new Socket(args[0], i))
            {
                availablePorts.add(i);
            }
            catch (ConnectException e)
            {
                // e.printStackTrace();
            }
            catch (UnknownHostException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        for (Integer i : availablePorts)
        {
            System.out.println(i);
        }
    }
}
