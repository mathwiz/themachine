package assembler;

class AInstruction extends Instruction {
    final String address;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: AInstruction code");
        } else {
            Instruction instruction = new AInstruction(args[0]);
            System.out.println("This is an AInstruction: " + instruction.isAInstruction());
            System.out.println("AInstruction: " + instruction);
        }
    }

    public AInstruction(String code) {
        super(code);
        short a = -1;
        try {
            a = Short.decode(code.substring(1));
        } catch (NumberFormatException e) {
            System.out.println("Creating symbolic AInstruction from " + this.code);
        }
        if (a == -1) {
            this.address = this.code.substring(1);

        } else {
            this.address = String.format("0%15s", Integer.toBinaryString(a)).replace(" ", "0");
        }
    }

    public String toString() {
        return address;
    }

    public boolean isSymbol() {
        return !address.startsWith("0");
    }

    public String getAddress() {
        return address;
    }
}

