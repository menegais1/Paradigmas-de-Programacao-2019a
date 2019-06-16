package Model;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NameList {

    private ArrayList<String> names;
    private String path;

    public NameList(String path) {
        this.path = path;
        this.names = new ArrayList<>();
    }

    public boolean load() {
        try {
            FileReader fr = new FileReader(this.path);
            BufferedReader bufferedReader = new BufferedReader(fr);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                names.add(line);
            }

        } catch (FileNotFoundException e) {
            System.err.println("O arquivo não foi encontrado");
            return false;
        } catch (IOException e) {
            System.err.println("Não foi possivel ler a linha no arquivo");
            return false;
        }
        return true;
    }

    public boolean isEmpty() {
        if (this.names == null) return true;
        return this.names.isEmpty();
    }

    public String pick() {
        return names.remove(0);
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public String getNamesAsString() {
        String str = "";
        for (int i = 0; i < names.size(); i++) {
            str += names.get(i) + "\n";
        }
        return str;
    }
}
