package assembler;

abstract class Instruction {
    final String code;

    public Instruction(String code) {
        this.code = code;
    }

    public final boolean isAInstruction() {
        return this.getClass() == AInstruction.class;
    }
}

