package assembler;

import java.util.BitSet;

class Code {
    final Instruction instruction;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: Code instruction");
        } else {
            Code code = new Code(args[0]);
            System.out.println(String.format("Code for %s is [%s]", args[0], code));
        }
    }

    public Code(String code) {
        this.instruction = createInstruction(code);
    }

    private Instruction createInstruction(String code) {
        return code.startsWith("@") ? new AInstruction(code) : new CInstruction(code);
    }

    public String toString() {
        return instruction.toString();
    }

    private static abstract class Instruction {
        final String code;

        public Instruction(String code) {
            this.code = code;
        }
    }

    private static class AInstruction extends Instruction {
        final String address;

        public AInstruction(String code) {
            super(code);
            short address = Short.decode(code.substring(1));
            this.address = String.format("0%15s", Integer.toBinaryString(address)).replace(" ", "0");
        }

        public String toString() {
            return address;
        }
    }

    private static class CInstruction extends Instruction {
        final String comp;
        final String dest;
        final String jump;

        public CInstruction(String code) {
            super(code);
            String[] jumpCut = code.split(";");
            jump = jumpCut.length > 1 ? jumpCut[1] : "";
            String[] cut1 = jumpCut[0].split("=");
            dest = cut1.length > 1 ? cut1[0] : "";
            comp = cut1.length > 1 ? cut1[1] : cut1[0];
        }

        public String toString() {
            return "111" + decodeComp(comp) + decodeDest(dest) + decodeJump(jump);
        }

        private String decodeComp(String s) {
            BitSet bits = new BitSet(8);
            bits.set(7);
            switch (s) {
                case "0":
                    bits.set(1);
                    bits.set(3);
                    bits.set(5);
                    break;
                case "1":
                    bits.set(1, 7);
                    break;
                case "-1":
                    bits.set(1);
                    bits.set(2);
                    bits.set(3);
                    bits.set(5);
                    break;
                case "D":
                    bits.set(3);
                    bits.set(4);
                    break;
                case "A":
                    bits.set(1);
                    bits.set(2);
                    break;
                case "!D":
                    bits.set(3);
                    bits.set(4);
                    break;
                case "-A":
                    bits.set(1);
                    bits.set(2);
                    bits.set(5);
                    bits.set(6);
                    break;
                case "D+1":
                    bits.set(2);
                    bits.set(3);
                    bits.set(4);
                    bits.set(5);
                    bits.set(6);
                    break;
                case "A+1":
                    bits.set(1);
                    bits.set(2);
                    bits.set(4);
                    bits.set(5);
                    bits.set(6);
                    break;
                case "D-1":
                    bits.set(3);
                    bits.set(4);
                    bits.set(5);
                    break;
                case "A-1":
                    bits.set(1);
                    bits.set(2);
                    bits.set(5);
                    break;
                case "D+A":
                    bits.set(5);
                    break;
                case "D-A":
                    bits.set(2);
                    bits.set(5);
                    bits.set(6);
                    break;
                case "A-D":
                    bits.set(4);
                    bits.set(5);
                    bits.set(6);
                    break;
                case "D&A":
                    break;
                case "D|A":
                    bits.set(2);
                    bits.set(4);
                    bits.set(6);
                    break;
                case "M":
                    bits.set(0);
                    bits.set(1);
                    bits.set(2);
                    break;
                case "!M":
                    bits.set(0);
                    bits.set(1);
                    bits.set(2);
                    bits.set(6);
                    break;
                case "-M":
                    bits.set(0);
                    bits.set(1);
                    bits.set(2);
                    bits.set(5);
                    bits.set(6);
                    break;
                case "M+1":
                    bits.set(0);
                    bits.set(1);
                    bits.set(2);
                    bits.set(4);
                    bits.set(5);
                    bits.set(6);
                    break;
                case "M-1":
                    bits.set(0);
                    bits.set(1);
                    bits.set(2);
                    bits.set(5);
                    break;
                case "D+M":
                    bits.set(0);
                    bits.set(5);
                    break;
                case "D-M":
                    bits.set(0);
                    bits.set(2);
                    bits.set(5);
                    bits.set(6);
                    break;
                case "M-D":
                    bits.set(0);
                    bits.set(4);
                    bits.set(5);
                    bits.set(6);
                    break;
                case "D&M":
                    bits.set(0);
                    break;
                case "D|M":
                    bits.set(0);
                    bits.set(2);
                    bits.set(4);
                    bits.set(6);
                    break;
            }
            return translateBits(bits);
        }

        private String decodeDest(String s) {
            BitSet bits = new BitSet(4);
            bits.set(3);
            switch (s) {
                case "M":
                    bits.set(2);
                    break;
                case "D":
                    bits.set(1);
                    break;
                case "MD":
                    bits.set(1);
                    bits.set(2);
                    break;
                case "A":
                    bits.set(0);
                    break;
                case "AM":
                    bits.set(0);
                    bits.set(2);
                    break;
                case "AD":
                    bits.set(0);
                    bits.set(1);
                    break;
                case "AMD":
                    bits.set(0);
                    bits.set(1);
                    bits.set(2);
                    break;
            }
            return translateBits(bits);
        }

        private String decodeJump(String s) {
            BitSet bits = new BitSet(4);
            bits.set(3);
            switch (s) {
                case "JGT":
                    bits.set(2);
                    break;
                case "JEQ":
                    bits.set(1);
                    break;
                case "JGE":
                    bits.set(1);
                    bits.set(2);
                    break;
                case "JLT":
                    bits.set(0);
                    break;
                case "JNE":
                    bits.set(0);
                    bits.set(2);
                    break;
                case "JLE":
                    bits.set(0);
                    bits.set(1);
                    break;
                case "JMP":
                    bits.set(0);
                    bits.set(1);
                    bits.set(2);
                    break;
            }
            return translateBits(bits);
        }

        private String translateBits(BitSet bits) {
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < bits.length(); i++) {
                out.append(bits.get(i) ? "1" : "0");
            }
            ;
            return out.toString().substring(0, bits.length() - 1);
        }
    }
}
