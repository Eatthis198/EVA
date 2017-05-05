package socket.tcp.mail;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by David on 02.05.2017.
 */
public class SendMail
{

    private static void sendMail(Socket socket, String helo, String sender, String receiver, String data, int count) throws IOException
    {

        try (
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {

            writer.write("HELO " + helo);
            writer.newLine();
            for (int i = 0; i < count; i++)
            {

                writer.write("MAIL FROM: <" + sender + ">");
                writer.newLine();
                writer.write("RCPT TO: <" + receiver + ">");
                writer.newLine();
                writer.write("DATA");
                writer.newLine();
                writer.write(data);
                writer.newLine();
                writer.write(".");
                writer.newLine();
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
    }

    public static void main(String[] args)
    {

        if (args.length != 7)
        {
            System.out.println("RUNCONFIG");
            return;
        }

        int count = Integer.parseInt(args[6]);

        try (Socket socket = new Socket(args[0], Integer.parseInt(args[1])))
        {
            sendMail(socket, args[2], args[3], args[4], args[5], count);
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
