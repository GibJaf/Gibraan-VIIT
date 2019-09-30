import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

class SymbolTableRow {
    private int address;
    private int index;

    public SymbolTableRow () {

    }

    public SymbolTableRow (int index, int address) {
        this.index = index;
        this.address = address;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

class SymbolTable {
    private HashMap<String, SymbolTableRow> hashMap = new HashMap<>();

    public void putSymbol(int index, String symbol, int address) {
        SymbolTableRow symbolTableRow = new SymbolTableRow(index, address);
        hashMap.put(symbol, symbolTableRow);
    }

    public void updateSymbol(String symbol, int address) {
        SymbolTableRow symbolTableRow = hashMap.get(symbol);
        symbolTableRow.setAddress(address);
        hashMap.put(symbol, symbolTableRow);
    }

    public boolean hasSymbol(String symbol) {
        return hashMap.containsKey(symbol);
    }

    public int getIndex(String symbol) {
        return hashMap.get(symbol).getIndex();
    }

    public void printTable() {
        for (String symbol : hashMap.keySet()) {
            SymbolTableRow symbolTableRow = hashMap.get(symbol);

            System.out.println(symbolTableRow.getIndex() + " " + symbol + " " + symbolTableRow.getAddress());
        }
    }
}

class LiteralTableRow {
    private String literal;
    private int address;

    public LiteralTableRow () {

    }

    public LiteralTableRow (String literal, int address) {
        this.literal = literal;
        this.address = address;
    }

    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }
}

class LiteralTable {
    private HashMap<Integer, LiteralTableRow> hashMap = new HashMap<>();

    public void addLiteral(int index, String literal, int address) {
        LiteralTableRow literalTableRow = new LiteralTableRow(literal, address);
        hashMap.put(index, literalTableRow);
    }

    public void updateLiteral(int index, int address) {
        LiteralTableRow literalTableRow = hashMap.get(index);
        literalTableRow.setAddress(address);
        hashMap.put(index, literalTableRow);
    }

    public LiteralTableRow getLiteral(int index) {
        return hashMap.get(index);
    }

    public void printTable() {
        for (Integer index : hashMap.keySet()) {
            LiteralTableRow literalTableRow = hashMap.get(index);

            System.out.println(index + " " + literalTableRow.getLiteral() + " " + literalTableRow.getAddress());
        }
    }
}

class PoolTable {
    private ArrayList<Integer> arrayList = new ArrayList<>();

    public void addPool(int pool) {
        arrayList.add(pool);
    }

    public int getLatestPool() {
        return arrayList.get(arrayList.size() - 1);
    }

    public void removeLatestPool() {
        arrayList.remove(arrayList.size() - 1);
    }

    public void printTable() {
        for (Integer pool : arrayList) {
            System.out.println(pool);
        }
    }
}

public class PassOne {

    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public void setIO(String input, String output) throws IOException {
        bufferedReader = new BufferedReader(new FileReader(input));
        bufferedWriter = new BufferedWriter(new FileWriter(output));
    }

    public static void main(String args[]) {

        try {
            PassOne passOne = new PassOne();
            passOne.setIO("abc.txt", "intermediate_code.txt");
            passOne.parseFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeCode(String code) throws IOException {
        System.out.println("code: " + code);
        bufferedWriter.write(code + "\n");
    }

    private void parseFile() throws IOException {
        String line;
        int lc = 0;
        int symPtr = 0;
        int litPtr = 0;

        MOT mot = new MOT();
        SymbolTable symbolTable = new SymbolTable();
        PoolTable poolTable = new PoolTable();
        poolTable.addPool(0);
        LiteralTable literalTable = new LiteralTable();

        while ((line = bufferedReader.readLine()) != null) {
            String parts[] = line.split("\\s+|\n");

            for (String part : parts) {
                System.out.print(part + " ");
            }
            System.out.print("\n");

            if (!parts[0].isEmpty()) {
                if (symbolTable.hasSymbol(parts[0])) {
                    symbolTable.updateSymbol(parts[0], lc);
                } else {
                    symbolTable.putSymbol(symPtr++, parts[0], lc);
                }

            }

            if (parts[1].equals("LTORG")) {
                System.out.println("In LTORG " + poolTable.getLatestPool() + " " + litPtr);

                for (int i = poolTable.getLatestPool(); i < litPtr; i++) {
                    LiteralTableRow literal = literalTable.getLiteral(i);

                    literalTable.updateLiteral(i, lc++);


                    writeCode("(DL, " + mot.getCode("DC") + ") " +
                            "(C, " + literal.getLiteral() + ")");
                }

                poolTable.addPool(litPtr);
            }

            if (parts[1].equals("START")) {
                lc = Integer.parseInt(parts[2]);

                writeCode("(AD, " + mot.getCode("START") + ") " +
                        "(C, " + lc + ")");
            }

            if (parts[1].equals("END")) {
                System.out.println("In end " + poolTable.getLatestPool() + " " + litPtr);

                writeCode("(AD, " + mot.getCode("END") + ")");

                for (int i = poolTable.getLatestPool(); i < litPtr; i++) {
                    LiteralTableRow literal = literalTable.getLiteral(i);

                    literalTable.updateLiteral(i, lc++);


                    writeCode("(DL, " + mot.getCode("DC") + ") " +
                            "(C, " + literal.getLiteral() + ")");
                }

                poolTable.addPool(litPtr);
            }

            if (parts[1].equals("DS")) {
                String size = parts[2].replace("'", "").replace("=", "");

                writeCode("(DL, " + mot.getCode("DS") + ") " +
                        "(C, " + size + ")");

                lc += Integer.parseInt(size);
            }

            if (parts[1].equals("DC")) {
                String constant = parts[2];

                writeCode("(DL, " + mot.getCode("DC") + ") " +
                        "(C, " + constant + ")");

                lc++;
            }

            if (mot.getType(parts[1]).equals("IS")) {
                String code = "(IS, " + mot.getCode(parts[1]) + ") ";

                int i = 2;

                while (i < parts.length) {

                    if (mot.getType(parts[i]).equals("RG")) {
                        code += "(RG, " + mot.getCode(parts[i]) + ") ";
                    } else if (parts[i].contains("=")) {
                        String literal = parts[i].replace("'", "").replace("=", "");

                        literalTable.addLiteral(litPtr, literal, -1);

                        code += "(L, " + (litPtr++) + ") ";
                    } else {
                        if (symbolTable.hasSymbol(parts[i])) {
                            int index = symbolTable.getIndex(parts[i]);

                            code += "(S, " + index + ") ";
                        } else {
                            symbolTable.putSymbol(symPtr++, parts[i], -1);

                            int index = symbolTable.getIndex(parts[i]);

                            code += "(S, " + index + ") ";
                        }
                    }

                    i++;
                }

                writeCode(code);
                lc++;
            }

        }

        bufferedWriter.close();
        bufferedReader.close();

        System.out.println();
        symbolTable.printTable();
        System.out.println();

        System.out.println();
        literalTable.printTable();
        System.out.println();

        poolTable.removeLatestPool();
        System.out.println();
        poolTable.printTable();
        System.out.println();
    }

}
