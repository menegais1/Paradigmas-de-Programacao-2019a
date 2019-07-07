package Controller;

import Model.Author;
import Model.Commit;
import Model.Repository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CommitAnalyzerController {

    private ObservableList<Repository> repositories;

    public CommitAnalyzerController() {
        List<Repository> list = new ArrayList<>();
        this.repositories = FXCollections.observableArrayList(list);
    }

    public void printRepositories() {
        for (Repository repository : repositories) {
            System.out.println(repository.toString());
        }
    }

    public void setInititalRepositories(List<String> repoUrls) {
        for (String repoUrl : repoUrls) {
            repositories.add(new Repository(repoUrl));
        }
    }

    public void getCommitsFromRepository() throws Exception {
        for (Repository repository : repositories) {
            getCommitsFromRepositoryAux(repository.getRepoUrl(), repository);
        }
    }

    private Repository getCommitsFromRepositoryAux(String repositoryUrl, Repository repository) throws Exception {

        URL url = null;
        url = new URL(repositoryUrl);

        HttpURLConnection con = null;

        con = (HttpURLConnection) url.openConnection();


        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", "Mozilla/5.0");


        BufferedReader in = null;

        in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));


        // Parse a nested JSON response using Gson
        JsonParser parser = new JsonParser();
        JsonArray results = null;
        try {
            results = parser.parse(in.readLine()).getAsJsonArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (JsonElement e : results) {
            JsonObject Jcommit = e.getAsJsonObject().getAsJsonObject("commit");
            JsonObject Jauthor = Jcommit.getAsJsonObject("author");
            Author author = new Author(Jauthor.get("name").getAsString(), Jauthor.get("email").getAsString(), Jauthor.get("date").getAsString());
            Commit commit = new Commit(Jcommit.get("message").getAsString(), author);
            repository.addCommit(commit);
        }
        repository.setMediumCommitMessageSize();

        in.close();

        String link;
        try {
            link = con.getHeaderFields().get("Link").get(0);
        } catch (Exception e) {
            return repository;
        }
        if (!link.contains("rel=\"last\"")) return repository;
        String nextPage = link
                .split(";")[0]
                .replace("<", "")
                .replace(">", "");

        return getCommitsFromRepositoryAux(nextPage, repository);
    }

    public ObservableList<Repository> getRepositories() {
        if (this.repositories == null) return null;
        return this.repositories;
    }

    public List<String> getFieldListPrettyNames() {
        return List.of("Repository", "Núm. Commits", "Tam. Médio das Mensagens");
    }

    public List<String> getFieldListNames() {
        return List.of("repoUrl", "commitNumber", "mediumCommitMessageSize");
    }

}
