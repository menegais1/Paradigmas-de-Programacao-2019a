package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DownloadController {

    public void downloadCsv(String url, String fileName) {
        System.out.println("Download file from " + url);
        InputStream in = null;
        try {
            in = new URL(url).openStream();
            Files.copy(in, Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File dowloaded at: " + fileName);
        } catch (IOException e) {
            System.out.println("Not possible to download file");
            e.printStackTrace();
        }
    }

}
