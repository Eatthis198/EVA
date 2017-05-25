package rmi.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ChatClientMainSimple {
    public static void main(String[] args) {
//        if (args.length != 2) {
//            System.out.println("usage: java " +
//                    "rmi.chat.ChatClientMainSimple " +
//                    "<server> <nick name>");
//            return;
//        }

        args = getAddresGroupAndName();

        try {
            Registry registry = LocateRegistry.getRegistry(args[0], 4711);
            ChatServer server = (ChatServer) registry.lookup("ChatServer" + args[1]);
            System.out.println("Server contacted.");
            ChatClientImplSimple client = new ChatClientImplSimple(args[2]);
            if (server.addClient(client)) {
                System.out.println("End by typing 'exit' or 'quit'.");
                sendInputToServer(server, args[2]);
                server.removeClient(client);
            } else {
                System.out.println("name already defined at server");
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        System.exit(0);
    }

    private static void sendInputToServer(ChatServer server,
                                          String name) {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = input.readLine()) != null) {
                if (line.equals("exit") || line.equals("quit")) {
                    break;
                }
                server.sendMessage(name, line);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private static String[] getAddresGroupAndName() {
        String result[] = new String[3];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter server address");
        result[0] = scanner.nextLine();
        System.out.println("Please enter server group");
        result[1] = scanner.nextLine();
        System.out.println("Please enter your name");
        result[2] = scanner.nextLine();

        return result;
    }

}
