package assembler;

class AInstruction extends Instruction {
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

