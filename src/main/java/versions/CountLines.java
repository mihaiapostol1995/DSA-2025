package versions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class CountLines {
    public static void main(String[] args) throws IOException {
        long count = Files.lines(Path.of("document.txt"))
                .filter(line -> !line.isBlank())  // skip empty/whitespace
                .count();

        System.out.println("Non-empty lines: " + count);
    }
}
