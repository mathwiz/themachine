package assembler;

import java.util.regex.Pattern;

class Line {
    final String text;
    final String programLine;
    private static final Pattern SYMBOL = Pattern.compile("^[a-zA-Z\\._$:][0-9a-zA-Z\\._$:]*");

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: Line teststring");
        } else {
            Line line = new Line(args[0]);
            System.out.println(String.format("Line is [%s]. Program Line is [%s]. A Intruction:%s, C Intruction:%s, Symbol:%s, Program:%s, Error:%s",
                    line.getText(), line.getProgramLine(), line.isAInstruction(), line.isCInstruction(), line.isSymbol(), line.isProgramLine(), line.hasSyntaxError()));
        }
    }

    Line(String line) {
        text = line == null ? "" : line;
        String s = text.indexOf("//") != -1 ? text.substring(0, text.indexOf("//")) : text;
        s = s.replaceAll("\\s", "");
        programLine = s;
    }

    boolean isAInstruction() {
        return programLine.length() > 1 && programLine.startsWith("@") && (programLine.substring(1).matches("\\d+") || validSymbolText(programLine.substring(1)));
    }

    boolean isCInstruction() {
        return isProgramLine() && !isAInstruction() && !isSymbol();
    }

    boolean isSymbol() {
        return programLine.length() > 2 && programLine.startsWith("(") && programLine.endsWith(")") && validSymbolText(programLine.substring(1, programLine.length() - 1));
    }

    boolean isProgramLine() {
        return programLine.length() > 0;
    }

    boolean hasSyntaxError() {
        if (programLine.length() > 1 && programLine.startsWith("@") && !(programLine.substring(1).matches("\\d+") || validSymbolText(programLine.substring(1)))) {
            return true;
        }
        if (programLine.length() > 2 && programLine.startsWith("(") && programLine.endsWith(")") && !validSymbolText(programLine.substring(1, programLine.length() - 1))) {
            return true;
        }
        return false;
    }

    private static boolean validSymbolText(String s) {
        return SYMBOL.matcher(s).matches();
    }

    String getText() {
        return text;
    }

    String getProgramLine() {
        return programLine;
    }
}
