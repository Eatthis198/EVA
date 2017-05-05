package socket.udp.relay;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by David on 11.04.2017.
 */
public class UDPRelay
{

    // private static SyncStack<Integer> ports;

    /**
     * Methode zum Starten des UDP-Relays.
     *
     * @param inSocket
     *            {@link DatagramSocket}, über das Datagramme zum Weiterleiten
     *            empfangen werden sollen.
     * @param addressToRelayTo
     *            Adresse des Rechners, an den die über inSocket empfagenen
     *            Datagramme weitergeleitet werden sollen.
     * @param portToRelayTo
     *            Portnummer, an die die über inSocket empfagenen Datagramme
     *            weitergeleitet werden sollen.
     * @param controlReader
     *            {@link Reader}, von dem gelesen werden soll, ob ein über
     *            inSocket empfangenes Datagramm weitergeleitet oder verworfen
     *            werden soll.
     * @throws IOException
     *             Wird ausgelöst, wenn es bei der UDP-Kommunikation zu einem
     *             Ein-/Ausgabefehler kommt.
     */
    public static void runUDPRelay(DatagramSocket inSocket, InetAddress addressToRelayTo, int portToRelayTo, Reader controlReader) throws IOException
    {
        // Hier ergänzen...
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 10, 101, TimeUnit.DAYS, new LinkedBlockingDeque<>());

        while (true)
        {
            DatagramPacket packet = new DatagramPacket(new byte[100], 100);
            inSocket.receive(packet);
            // System.out.println("soll das packet weitergeleitet werden?");
            // char input = (char) controlReader.read();
            // System.out.println("input read!");
            // if (input == 'j')
            // {
            // System.out.println("packet wird weitergeleitet!");
            pool.execute(new RelayServerThread(portToRelayTo, addressToRelayTo, packet, controlReader));
            // }

            // controlReader.reset();
          //  controlReader = new InputStreamReader(System.in);

        }

    }

    /**
     * Hauptprogramm. Als Kommandozeilenargumente sollen die Portnummer für den
     * Empfang von Datagrammen sowie Adresse und Port für die Weiterleitung der
     * empfangenen Datagramme angegeben werden.
     */
    public static void main(String[] args) throws IOException
    {
        if (args.length != 3)
        {
            return;
        }

        // ports = new SyncStack<>();

        int inPort = Integer.parseInt(args[0]);
        InetAddress addressToRelayTo = InetAddress.getByName(args[1]);
        int portToRelayTo = Integer.parseInt(args[2]);

        // for (int i = 4711; i < 4711 + 10; i++)
        // {
        //
        // if (i != inPort && i != portToRelayTo)
        // {
        // ports.push(i);
        // }
        // }
        Reader controlReader = new InputStreamReader(System.in);
        try (DatagramSocket dgSocket = new DatagramSocket(inPort))
        {
            UDPRelay.runUDPRelay(dgSocket, addressToRelayTo, portToRelayTo, controlReader);
        }
    }
}

class RelayServerThread implements Runnable
{

    private int port, clientPort;

    private InetAddress address, clientAddress;

    private DatagramPacket packet;

    // private SyncStack<Integer> ports;

    private Reader controlReader;

    public RelayServerThread(int port, InetAddress adress, DatagramPacket packet, Reader controlReader)
    {
        this.port = port;
        this.clientPort = packet.getPort();
        this.address = adress;
        this.clientAddress = packet.getAddress();
        this.packet = packet;
        this.controlReader = controlReader;
    }

    private static boolean shouldRelay(Reader controlReader)
    {
        //controlReader = new InputStreamReader(System.in);
        boolean result = false;
        try
        {
            char in[] = new char[20];
            controlReader.read(in);
            System.out.println("INPUT:" + in[0]);
            if (in[0] == 'j')
            {
                result = true;
            }
        }
        catch (IOException e)
        {
            System.out.println("READER ABGESCHISSEN");
            e.printStackTrace();
        }
        System.out.println("Input was: " + String.valueOf(result));
        return result;
    }

    @Override
    public void run()
    {
        // int sockPort = ports.pop();
        try (DatagramSocket sock = new DatagramSocket())
        {

            System.out.println("soll das packet an den server geleitet werden");
            //controlReader.close();
            if (!shouldRelay(controlReader))
            {
                return;
            }

            packet.setAddress(address);
            packet.setPort(port);
            sock.send(packet);

            System.out.println("packet sent to server!");

            sock.receive(packet);
            System.out.println("packet received from server!");
            // fragen ob ich schicken soll
            // controlReader.reset();
            // controlReader = new InputStreamReader(System.in);
            System.out.println("soll das packet an den client gesendet werden");
            // if ((char) controlReader.read() != 'j')
            // {
            // return;
            // }
            //controlReader.close();
            if (!shouldRelay(controlReader))
            {
                return;
            }

            packet.setPort(clientPort);
            packet.setAddress(clientAddress);

            sock.send(packet);

            System.out.println("packet sent to client");
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}