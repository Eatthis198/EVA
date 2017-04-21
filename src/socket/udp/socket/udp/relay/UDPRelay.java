package socket.udp.socket.udp.relay;

import util.SyncStack;

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
public class UDPRelay {

    private static SyncStack<Integer> ports;


    /**
     * Methode zum Starten des UDP-Relays.
     *
     * @param inSocket         {@link DatagramSocket}, über das
     *                         Datagramme zum Weiterleiten empfangen werden sollen.
     * @param addressToRelayTo Adresse des Rechners, an den die
     *                         über inSocket empfagenen Datagramme weitergeleitet werden
     *                         sollen.
     * @param portToRelayTo    Portnummer, an die die über inSocket
     *                         empfagenen Datagramme weitergeleitet werden sollen.
     * @param controlReader    {@link Reader}, von dem gelesen
     *                         werden soll, ob ein über inSocket empfangenes Datagramm
     *                         weitergeleitet oder verworfen werden soll.
     * @throws IOException Wird ausgelöst, wenn es bei der
     *                     UDP-Kommunikation zu einem Ein-/Ausgabefehler kommt.
     */
    public static void runUDPRelay(DatagramSocket inSocket,
                                   InetAddress addressToRelayTo,
                                   int portToRelayTo,
                                   Reader controlReader)
            throws IOException {
        // Hier ergänzen...
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 10, 101,
                TimeUnit.DAYS, new LinkedBlockingDeque<>());


        while (true) {
            DatagramPacket packet = new DatagramPacket(new byte[100], 100);
            inSocket.receive(packet);

            System.out.println("soll das packet weitergeleitet werden?");
            char input = (char) controlReader.read();
            System.out.println("input read!");
            if (input == 'j') {
                System.out.println("packet wird weitergeleitet!");
                pool.execute(new RelayServerThread(ports, portToRelayTo, addressToRelayTo,
                        packet));
            }

            controlReader.reset();
            //controlReader = new InputStreamReader(System.in);

        }

    }


    /**
     * Hauptprogramm. Als Kommandozeilenargumente sollen die
     * Portnummer für den Empfang von Datagrammen sowie Adresse
     * und Port für die Weiterleitung der empfangenen Datagramme
     * angegeben werden.
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 3)
            return;

        ports = new SyncStack<>();

        int inPort = Integer.parseInt(args[0]);
        InetAddress addressToRelayTo =
                InetAddress.getByName(args[1]);
        int portToRelayTo = Integer.parseInt(args[2]);

        for (int i = 4711; i < 4711 + 10; i++)
            if (i != inPort && i != portToRelayTo) ports.push(i);

        Reader controlReader = new InputStreamReader(System.in);
        try (DatagramSocket dgSocket = new DatagramSocket(inPort)) {
            UDPRelay.runUDPRelay(dgSocket, addressToRelayTo,
                    portToRelayTo, controlReader);
        }
    }
}

class RelayServerThread implements Runnable {

    private int port, clientPort;
    private InetAddress address, clientAddress;
    private DatagramPacket packet;
    private SyncStack<Integer> ports;

    public RelayServerThread(SyncStack<Integer> ports, int port, InetAddress adress, DatagramPacket packet) {
        this.port = port;
        this.clientPort = packet.getPort();
        this.address = adress;
        this.clientAddress = packet.getAddress();
        this.packet = packet;
        this.ports = ports;
    }

    @Override
    public void run() {
        int sockPort = ports.pop();
        try (DatagramSocket sock = new DatagramSocket(sockPort)) {

            packet.setAddress(address);
            packet.setPort(port);
            sock.send(packet);

            System.out.println("packet sent to server!");

            sock.receive(packet);
            // fragen ob ich schicken soll

            System.out.println("packet received from server!");

            packet.setPort(clientPort);
            packet.setAddress(clientAddress);

            sock.send(packet);

            System.out.println("packet sent to client");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ports.push(sockPort);
        }
    }
}