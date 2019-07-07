package Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UrlFileLoaderController {

    private List<String> urlList;

    public UrlFileLoaderController() {
        this.urlList = new ArrayList<>();
    }


    public List<String> getUrlList() {
        return urlList;
    }

    public boolean load(String path) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fr);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            this.urlList.add(line);
        }

        return true;
    }

    public boolean isEmpty() {
        return urlList.isEmpty();
    }
}
