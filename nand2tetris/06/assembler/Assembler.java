package assembler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public abstract class Assembler {
    protected final FileIO source;
    protected final FileIO output;

    protected Assembler(String filename) {
        try {
            NameHandler files = new NameHandler(filename);
            source = new FileIO(files.getInputFile());
            output = new FileIO(files.getOutputFile());
            List<String> lines = processInput();
            processOutput(lines);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract List<String> processInput() throws IOException;

    protected final void processOutput(List<String> list) throws IOException {
        output.writeLines(list);
    }
}