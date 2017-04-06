package uebung2;

import util.DirectoryPrinter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by David on 06.04.2017.
 */
public class FileWalker {

    public static void main(String args[]) {

        Path path = FileSystems.getDefault().getPath("");


        try (FileOutputStream fos = new FileOutputStream("TreeView.txt");
             PrintStream ps = new PrintStream(fos)){
            //Files.walkFileTree(path, new MyFileVisitor());

            // UTIL TEST

            DirectoryPrinter.printTree(path,System.out,true);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class MyFileVisitor implements FileVisitor<Path> {

    private int depth = 0;

    private void printTabs() {
       // System.out.print("|");
        for (int i = 0; i < depth; i++)
            System.out.print("|\t");
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

        printTabs();
        System.out.println(dir.toAbsolutePath().toString());
        depth++;

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        printTabs();

        System.out.println(file.toAbsolutePath().toString());
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