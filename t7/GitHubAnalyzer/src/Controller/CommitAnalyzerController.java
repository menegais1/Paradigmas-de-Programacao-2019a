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
    private Repository mostCommitsRepo;
    private Repository lessCommitsRepo;
    private Repository mostRecentCommitRepo;
    private Repository olderCommitRepo;

    public CommitAnalyzerController() {
        List<Repository> list = new ArrayList<>();
        this.repositories = FXCollections.observableArrayList(list);
    }

    public String getMostCommitsRepo() {
        String text = "Repositório com mais commits: ";
        if (mostCommitsRepo == null) return text;
        return text + mostCommitsRepo.getRepoUrl();
    }

    public void setMostCommitsRepo(Repository mostCommitsRepo) {
        this.mostCommitsRepo = mostCommitsRepo;
    }

    public String getLessCommitsRepo() {
        String text = "Repositório com menos commits: ";
        if (lessCommitsRepo == null) return text;
        return text + lessCommitsRepo.getRepoUrl();
    }

    public void setLessCommitsRepo(Repository lessCommitsRepo) {
        this.lessCommitsRepo = lessCommitsRepo;
    }

    public String getMostRecentCommitRepo() {
        String text = "Repositório com commit mais recente: ";
        if (mostRecentCommitRepo == null) return text;
        return text + mostRecentCommitRepo.getRepoUrl();
    }

    public void setMostRecentCommitRepo(Repository mostRecentCommitRepo) {
        this.mostRecentCommitRepo = mostRecentCommitRepo;
    }

    public String getOlderCommitRepo() {
        String text = "Repositório com commit mais antigo: ";
        if (olderCommitRepo == null) return text;
        return text + olderCommitRepo.getRepoUrl();
    }

    public void setOlderCommitRepo(Repository olderCommitRepo) {
        this.olderCommitRepo = olderCommitRepo;
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
        System.out.println("Buscando repositórios");
        mostCommitsRepo = lessCommitsRepo = mostRecentCommitRepo = olderCommitRepo = repositories.get(0);
        for (Repository repository : repositories) {
            getCommitsFromRepositoryAux(repository.getRepoUrl(), repository);
            if (repository.getCommitNumber() > mostCommitsRepo.getCommitNumber()) mostCommitsRepo = repository;
            if (repository.getCommitNumber() < lessCommitsRepo.getCommitNumber()) lessCommitsRepo = repository;
            if (repository.getLastCommitDate().after(mostRecentCommitRepo.getLastCommitDate()))
                mostRecentCommitRepo = repository;
            if (repository.getFirstCommitDate().before(olderCommitRepo.getFirstCommitDate()))
                olderCommitRepo = repository;
        }
        System.out.println("Repositórios buscados");
    }

    public void printRepositoriesInfo() {
        System.out.println("Repositório com mais commits: " + mostCommitsRepo.toString());
        System.out.println("Repositório com menos commits: " + lessCommitsRepo.toString());
        System.out.println("Repositório com commit mais recente: " + mostRecentCommitRepo.toString());
        System.out.println("Repositório com commit mais antigo: " + olderCommitRepo.toString());
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
        JsonParser parser = new JsonParser();
        JsonArray results = null;
        results = parser.parse(in.readLine()).getAsJsonArray();
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
