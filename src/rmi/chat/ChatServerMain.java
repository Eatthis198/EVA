package rmi.chat;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Created by David on 18.05.2017.
 */
public class ChatServerMain {

    public static void main(String[] args) {
        try {
            int groupCount = getGroupCount();
            ChatServerImpl servers[] = new ChatServerImpl[groupCount];
            for (int i = 0; i < groupCount; i++) {
                servers[i] = new ChatServerImpl();
            }

            Registry registry = LocateRegistry.createRegistry(4711);

            for (int i = 0; i < groupCount; i++) {
                registry.rebind("ChatServer" + i, servers[i]);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private static int getGroupCount() {
        int result = 1;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("How many groups should i set up?");
            result = Integer.parseInt(scanner.nextLine());
        }
        System.out.println("Created: " + result + " groups!");
        return result;
    }
}
