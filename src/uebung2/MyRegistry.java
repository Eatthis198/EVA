package uebung2;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Created by David on 06.04.2017.
 */
public class MyRegistry {

    public static void main(String[] args) {

        FileSystem fileSystem = FileSystems.getDefault();
        try (WatchService ws = fileSystem.newWatchService()) {

            Path p = fileSystem.getPath("").toAbsolutePath();

            WatchEvent.Kind<?>[] events = {ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE};
            p.register(ws, events);


            while (true) {

                System.out.println("Waiting for event in: " + p.toFile().toString());
                watch(ws);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void watch(WatchService ws) {
        try {
            WatchKey key = ws.take();

            if (key.isValid())
                for (WatchEvent e : key.pollEvents()) {
                    String s = null;
                    if (e.kind() == ENTRY_MODIFY)
                        s = " was modified!";
                    else if (e.kind() == ENTRY_CREATE)
                        s = " was created!";
                    else if (e.kind() == ENTRY_DELETE)
                        s = " was deleted!";

                    System.out.println("\nEvent: " + e.context() + s);
                    System.out.println("--------------------------------------------------------\n");
                }

            key.reset();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
