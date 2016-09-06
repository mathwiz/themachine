package assembler;

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
}
