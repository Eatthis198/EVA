package socket.tcp.mail;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by David on 02.05.2017.
 */
public class ReceiveMail
{

    private static void receiveMail(Socket socket, int count, String login, String pass) throws IOException
    {

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        writer.write("USER " + login);
        writer.newLine();
        writer.write("PASS " + pass);
        writer.newLine();
        writer.flush();
        for (int i = 1; i < count + 1; i++)
        {
            writer.write("RETR " + i);
            writer.newLine();
            writer.flush();
        }
        writer.write("QUIT");
        writer.newLine();
        writer.flush();
        while (true)
        {
            String tmp = reader.readLine();
            if (tmp == null)
            {
                break;
            }
            else
            {
                System.out.println(tmp);
            }
        }

    }

    public static void main(String[] args)
    {

        if (args.length != 5)
        {
            System.out.println("RUNCONFIG");
            return;
        }

        try (Socket socket = new Socket(args[0], Integer.parseInt(args[1])))
        {

            receiveMail(socket, Integer.parseInt(args[2]), args[3], args[4]);

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

}
