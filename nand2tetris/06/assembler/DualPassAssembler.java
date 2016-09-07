package assembler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public final class DualPassAssembler extends Assembler {
    private SymbolTable table = new SymbolTable();
    private InstructionList instructions = new InstructionList();

    public static void main(String[] args) {
        System.out.println(String.format("Starting DualPassAssembler on %s", args[0]));
        DualPassAssembler assembler = new DualPassAssembler(args[0]);
        assembler.assemble();
    }

    DualPassAssembler(String filename) {
        super(filename);
    }

    protected List<String> processInput() throws IOException {
        firstPass();
        secondPass();
        return instructions.toProgramLines();
    }

    private void firstPass() throws IOException {
        source.readLines().forEach((x) -> {
            Line line = new Line(x);
            if (line.hasSyntaxError()) {
                throw new RuntimeException("Syntax Error: " + line.getText());
            } else if (line.isSymbol()) {
                table.addLabel(line.getProgramLine(), instructions.getNextLineNumber());
            } else if (line.isAInstruction() || line.isCInstruction()) {
                instructions.add(new Code(line.getProgramLine()));
            } else {
                System.out.println("Skipping non-program line: " + line.getText());
            }
        });
    }

    private void secondPass() {
        for (ListIterator<Code> it = instructions.iterator(); it.hasNext(); ) {
            Code current = it.next();
            it.set(translateAInstruction(current));
        }
    }

    private Code translateAInstruction(Code code) {
        Instruction i = code.getInstruction();
        if (i.isAInstruction()) {
            AInstruction a = (AInstruction) i;
            System.out.println("Processing AInstruction: " + a);
            if (a.isSymbol()) {
                int address = table.retrieve(a.getAddress());
                System.out.println("Variable: " + a + " given address " + address);
                return new Code("@" + address);
            }
        }
        return code;
    }
}