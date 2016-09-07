package assembler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public final class SinglePassAssembler extends Assembler {
    public static void main(String[] args) {
        System.out.println(String.format("Starting SinglePassAssembler on %s", args[0]));
        SinglePassAssembler assembler = new SinglePassAssembler(args[0]);
        assembler.assemble();
    }

    SinglePassAssembler(String filename) {
        super(filename);
    }

    protected List<String> processInput() throws IOException {
        List<String> list = new LinkedList();
        source.readLines().forEach((x) -> {
            Line line = new Line(x);
            if (line.hasSyntaxError()) {
                throw new RuntimeException("Syntax Error: " + line.getText());
            } else if (line.isAInstruction() || line.isCInstruction()) {
                list.add(new Code(line.getProgramLine()).toString());
            } else {
                System.out.println("Skipping non-program line: " + line.getText());
            }
        });
        return list;
    }
}