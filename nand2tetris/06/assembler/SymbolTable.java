package assembler;

import java.util.HashMap;
import java.util.Map;

class SymbolTable {
    Map<String, Integer> symbols = new HashMap();
    int currentMemory;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: AInstruction code");
        } else {
            Instruction instruction = new AInstruction(args[0]);
            System.out.println("This is an AInstruction: " + instruction.isAInstruction());
            System.out.println("AInstruction: " + instruction);
        }
    }

    SymbolTable() {
        symbols.put("SP", 0);
        symbols.put("LCL", 1);
        symbols.put("ARG", 2);
        symbols.put("THIS", 3);
        symbols.put("THAT", 4);
        symbols.put("R0", 0);
        symbols.put("R1", 1);
        symbols.put("R2", 2);
        symbols.put("R3", 3);
        symbols.put("R4", 4);
        symbols.put("R5", 5);
        symbols.put("R6", 6);
        symbols.put("R7", 7);
        symbols.put("R8", 8);
        symbols.put("R9", 9);
        symbols.put("R10", 10);
        symbols.put("R11", 11);
        symbols.put("R12", 12);
        symbols.put("R13", 13);
        symbols.put("R14", 14);
        symbols.put("R15", 15);
        symbols.put("SCREEN", 16384);
        symbols.put("KBD", 24576);

        currentMemory = 16;
    }

    void addLabel(String label, int lineNumber) {
        String symbol = label.substring(1, label.length() - 1);
        symbols.put(symbol, lineNumber);
    }

    private int addVariable(String variable) {
        int index = currentMemory++;
        symbols.put(variable, index);
        return index;
    }

    int retrieve(String symbol) {
        Integer v = symbols.get(symbol);
        if (v == null) {
            v = addVariable(symbol);
        }
        return v;
    }
}
