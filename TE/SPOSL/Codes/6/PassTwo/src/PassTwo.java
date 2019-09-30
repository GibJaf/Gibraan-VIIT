import java.io.*;
import java.util.HashMap;

class SymbolTableRow {

    private String symbol;
    private int address;

    public SymbolTableRow(String symbol, int address) {
         this.symbol = symbol;
         this.address = address;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }
}

class SymbolTable {
    private HashMap<Integer, SymbolTableRow> hashMap = new HashMap<>();

    public void addSymbol(int index, String symbol, int address) {
        hashMap.put(index, new SymbolTableRow(symbol, address));
    }

    public int getAddress(int index) {
        return hashMap.get(index).getAddress();
    }
}

public class PassTwo {

    private BufferedReader symbolReader;
    private BufferedReader passOneReader;
    private BufferedWriter outputWriter;

    public void setIO(String input, String symbolTable, String output) throws IOException {
        passOneReader = new BufferedReader(new FileReader(input));
        symbolReader = new BufferedReader(new FileReader(symbolTable));
        outputWriter = new BufferedWriter(new FileWriter(output));
    }

    public static void main(String []args) {
        try {
            PassTwo passTwo = new PassTwo();

            passTwo.setIO("PassOp.txt", "sym.txt", "output.txt");


            passTwo.parseFile(passTwo.parseSymbolTable());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeCode(String code) throws IOException {
        outputWriter.write(code + "\n");
    }

    public SymbolTable parseSymbolTable() throws IOException {
        String line;
        SymbolTable symbolTable = new SymbolTable();

        while ((line = symbolReader.readLine()) != null) {
            String parts[] = line.split("\\s+|\n");

            symbolTable.addSymbol(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]));
        }

        return symbolTable;
    }

    public void parseFile(SymbolTable symbolTable) throws IOException {
        String line;
        int lc = Integer.parseInt(passOneReader.readLine()
                .replace("(", "")
                .replace(")", "")
                .replace(",", " ")
                .split("\\s+|\n")[3]);

        while ((line = passOneReader.readLine()) != null) {

            String processedLine = line
                    .replace("(", "")
                    .replace(")", "")
                    .replace(",", " ");

            String parts[] = processedLine.trim().split("\\s+|\n");

            String code = "";

            for (int i = 0; i < parts.length; i += 2) {
                String instruction = parts[i];

                if (instruction.trim().equals("")) {
                    continue;
                }

                System.out.println(lc + " " + instruction);

                if (instruction.equals("IS")) {
                    code += (lc + " " + parts[i + 1] + " ");
                }

                if (instruction.equals("RG")) {
                    code += parts[i + 1] + " ";
                }

                if (instruction.equals("S")) {
                    code += symbolTable.getAddress(Integer.parseInt(parts[i + 1])) + " ";
                }

                if (instruction.equals("C")) {
                    code += parts[i + 1] + " ";
                }

                if (instruction.equals("DL")) {
                    code += (lc + " " + parts[i + 1] + " ");
                }
            }

            writeCode(code);

            lc++;
        }

        symbolReader.close();
        passOneReader.close();
        outputWriter.close();

    }

}
