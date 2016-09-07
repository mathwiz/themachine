package assembler;

import java.util.ListIterator;
import java.util.LinkedList;
import java.util.List;

class InstructionList implements Iterable<Code> {
    List<Code> instructions = new LinkedList();

    InstructionList() {
    }

    public ListIterator<Code> iterator() {
        return instructions.listIterator();
    }

    void add(Code code) {
        instructions.add(code);
    }

    Code get(int lineNumber) {
        return instructions.get(lineNumber);
    }

    int getNextLineNumber() {
        return instructions.size();
    }

    List<String> toProgramLines() {
        List<String> list = new LinkedList();
        for (Code it : instructions) {
            list.add(it.toString());
        }
        return list;
    }
}
