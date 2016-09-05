package assembler;

public class NameHandler {
    final String inputFile;
    final String outputFile;

    public static void main(String[] args) {
        System.out.println("Usage: NameHandler inputFile outputFile");
        NameHandler handler = new NameHandler(args[0]);
        System.out.println(String.format("inputFile: %s", handler.getInputFile()));
        System.out.println(String.format("outputFile: %s", handler.getOutputFile()));
    }

    NameHandler(String filename) {
        inputFile = filename;
        String basename = filename.split("\\.")[0];
        outputFile = basename + ".hack";
    }

    public String getInputFile() {
        return inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }
}