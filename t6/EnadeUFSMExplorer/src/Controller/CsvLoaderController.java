package Controller;


import Model.EnadeRow;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvLoaderController {

    private ObservableList<EnadeRow> enadeRows;
    private String path;
    private String url;
    public static final String DEFAULT_URL = "https://docs.google.com/spreadsheets/d/e/2PACX-1vTO06Jdr3J1kPYoTPRkdUaq8XuslvSD5--FPMht-ilVBT1gExJXDPTiX0P3FsrxV5VKUZJrIUtH1wvN/pub?gid=0&single=true&output=csv";
    public static final String DEFAULT_PATH = "enade.csv";

    public CsvLoaderController(String path) {
        this.path = path;
        this.url = DEFAULT_URL;
    }

    public CsvLoaderController() {
        this.path = DEFAULT_PATH;
        this.url = DEFAULT_URL;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String loadCsv(boolean online) {
        if (path == null) return null;

        Reader reader = null;
        try {
            File f = Paths.get(this.path).toFile();
            if (!f.exists() || online) {
                DownloadController downloadController = new DownloadController();
                String error = downloadController.downloadCsv(this.url, this.path);
                if (error != null) return error;
            }

            reader = Files.newBufferedReader(f.toPath());

            CsvToBeanBuilder<EnadeRow> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(EnadeRow.class);
            CsvToBean<EnadeRow> csvToBean = csvToBeanBuilder.build();

            List<EnadeRow> list = csvToBean.parse();
            this.enadeRows = FXCollections.observableArrayList(list);

        } catch (FileNotFoundException e) {
            return "File not found";
        } catch (IOException e) {
            return "Error on reading file";
        }

        return null;
    }

    public ObservableList<EnadeRow> getRows() {
        if (this.enadeRows == null) return null;
        return this.enadeRows;
    }

    public List<String> getFieldListPrettyNames() {
        return EnadeRow.getFieldListPrettyNames();
    }

    public List<String> getFieldListNames() {
        return EnadeRow.getFieldListNames();
    }

    public String getFieldValueByName(EnadeRow row, String field) {
        String value = null;
        switch (field) {
            case "Ano":
                value = row.getAno();
                break;
            case "Prova":
                value = row.getProva();
                break;
            case "Tipo Questão":
                value = row.getTipoQuestao();
                break;
            case "Id Questão":
                value = row.getIdQuestao();
                break;
            case "Objeto":
                value = row.getObjeto();
                break;
            case "Acertos Curso":
                value = row.getAcertosCurso();
                break;
            case "Acertos Região":
                value = row.getAcertosRegiao();
                break;
            case "Acertos Brasil":
                value = row.getAcertosBrasil();
                break;
            case "Dif. (Curso-Brasil)":
                value = row.getAcertosDif();
                break;
            case "Gabarito":
                value = row.getGabarito();
                break;
            case "Imagem":
                value = row.getImagem();
                break;
            default:
                value = null;
        }

        return value;
    }
}
