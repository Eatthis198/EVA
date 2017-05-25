package rmi.messwerte;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Random;

/**
 * Created by David on 25-May-17.
 */
public class MesswertHassendeErfasser {

    public MesswertHassendeErfasser(String address, int port, int interval, int range) {

        while (true) {
            try (DatagramSocket socket = new DatagramSocket();
                 ByteArrayOutputStream bos = new ByteArrayOutputStream();
                 DataOutputStream dos = new DataOutputStream(bos);) {


                Random rnd = new Random();
                int number = rnd.nextInt(range);
                dos.writeInt(number);

                byte buff[] = bos.toByteArray();
                DatagramPacket packet = new DatagramPacket(buff, buff.length);
                packet.setAddress(InetAddress.getByName(address));
                packet.setPort(port);

                socket.send(packet);

                System.out.println("MesswertHassenderErfasser has sent:" + number);

                Thread.sleep(interval);

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
