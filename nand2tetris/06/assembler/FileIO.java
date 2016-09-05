package assembler;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

class FileIO {
    final Path path;

    public static void main(String[] args) {
        try {
            System.out.println(String.format("Testing FileIO reader on %s\n", args[0]));
            FileIO read = new FileIO(args[0]);
            read.readLines().forEach((x) -> {
                System.out.println(String.format("%s", x));
            });

            System.out.println();
            System.out.println(String.format("Testing FileIO writer on %s\n", args[1]));
            FileIO write = new FileIO(args[1]);
            List<String> lines = new LinkedList<String>();
            for (int i = 0; i < 10; i++) {
                lines.add(String.format("%d: Test line in %s", i, args[1]));
            }
            write.writeLines(lines);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    FileIO(String filename) {
        this.path = FileSystems.getDefault().getPath(filename);
    }

    List<String> readLines() throws IOException {
        return Files.readAllLines(path);
    }

    void writeLines(Iterable<String> lines) throws IOException {
        Files.write(path, lines);
    }
}