package uebung3;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by David on 20.04.2017.
 */
public class DNSClient {

    public static void main(String args[]) {

        String realArgs[] = {"dieseurlgibbetnix", "google.com","172.217.21.238"};

        for (String s : realArgs) {
            try {
                InetAddress add[] = InetAddress.getAllByName(s);
                for (InetAddress a : add) {
                    System.out.println(a);
                    boolean bReachable = a.isReachable(1337);
                    System.out.println("isReachable: " + bReachable);
                    System.out.println("------------------------------");
                }
            } catch (UnknownHostException e) {
                System.err.println(s + " war nix!");
                System.out.println("------------------------------");
                continue;
            } catch (IOException e) {
                continue;
            }
        }

    }

}
