package util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

/**
 * Created by David on 06.04.2017.
 */
public class DirectoryPrinter {


    public static void printTree(Path path, PrintStream printStream, boolean deleter) throws IOException {

        //Files.walkFileTree(path, new MyFileVisitor(printStream, deleter));
        walkFileTreeRecursive(path, new MyFileVisitor(printStream, deleter));
    }

    private static FileVisitResult walkFileTreeRecursive(Path path, FileVisitor<Path> visitor) throws IOException {

        File file = path.toAbsolutePath().toFile();
        // File file = path.toFile();
        if (file.isDirectory()) {
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            visitor.preVisitDirectory(path, attrs);

            File children[] = file.listFiles();
            for (File f : children) {
                walkFileTreeRecursive(f.toPath(), visitor);
            }
            return visitor.postVisitDirectory(path, null);
        }
        // not a directory

        return visitor.visitFile(path, Files.readAttributes(path, BasicFileAttributes.class));

    }

}

class MyFileVisitor implements FileVisitor<Path> {

    private int depth = 0;

    private PrintStream printStream;

    private boolean bDeleter;

    public MyFileVisitor(PrintStream printStream, boolean deleter) {
        this.printStream = printStream;
        bDeleter = deleter;
    }

    private void printTabs() {
        for (int i = 0; i < depth; i++)
            printStream.print("|\t");
    }

    private boolean deleteionDialog(Path file) {

        boolean bDeleted = false;


        System.out.println("Do you want to delete the file: " + file.toAbsolutePath().toString() + " (y,N)");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String fileName = file.getFileName().toString();

        if (input.contains("y")) {
            if (file.toFile().delete()) {
                System.out.println("File: " + fileName + " has been deleted!");
                bDeleted = true;
            } else {
                System.out.println("File could not be deleted");
            }
        } else if (input.contains("n") || input.isEmpty()) {
            System.out.println("File: " + fileName + " has NOT been deleted!");
        }

        return bDeleted;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

        if (printStream == null)
            return FileVisitResult.CONTINUE;

        printTabs();
        printStream.println(dir.toAbsolutePath().toString());
        depth++;

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if (bDeleter && file.getFileName().toString().startsWith("~")) {
            deleteionDialog(file);
        }

        if (printStream == null) {
            return FileVisitResult.CONTINUE;
        }


        printTabs();

        printStream.println(file.toAbsolutePath().toString());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        depth--;
        return FileVisitResult.CONTINUE;
    }
}


