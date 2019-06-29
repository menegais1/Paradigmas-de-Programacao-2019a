package Controller;


import Model.EnadeRow;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvLoaderController {

    private List<EnadeRow> enadeRows;
    private String path;
    private final String DEFAULT_URL = "https://docs.google.com/spreadsheets/d/e/2PACX-1vTO06Jdr3J1kPYoTPRkdUaq8XuslvSD5--FPMht-ilVBT1gExJXDPTiX0P3FsrxV5VKUZJrIUtH1wvN/pub?gid=0&single=true&output=csv";
    private final String DEFAULT_PATH = "enade.csv";

    public CsvLoaderController(String path) {
        this.enadeRows = new ArrayList<>();
        this.path = path;
    }

    public CsvLoaderController() {
        this.enadeRows = new ArrayList<>();
        this.path = DEFAULT_PATH;
    }

    public void loadCsv() {
        if (path == null) return;

        Reader reader = null;
        try {
            File f = Paths.get(this.path).toFile();
            if (!f.exists()) {
                DownloadController downloadController = new DownloadController();
                downloadController.downloadCsv(DEFAULT_URL, this.path);
            }

            reader = Files.newBufferedReader(f.toPath());

            CsvToBeanBuilder<EnadeRow> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(EnadeRow.class);
            CsvToBean<EnadeRow> csvToBean = csvToBeanBuilder.build();

            this.enadeRows = csvToBean.parse();

        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    public ObservableList<EnadeRow> getRows() {
        if (this.enadeRows == null) return null;
        ObservableList<EnadeRow> observableList = FXCollections.observableArrayList(this.enadeRows);
        return observableList;
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
