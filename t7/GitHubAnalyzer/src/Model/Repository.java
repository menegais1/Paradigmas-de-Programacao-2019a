package Model;

import java.util.ArrayList;
import java.util.List;

public class Repository {


    private String repoUrl;
    private List<Commit> commitList;
    private int commitNumber;
    private int totalCommitMessageSize;
    private float mediumCommitMessageSize;

    public Repository(String repoUrl) {
        this.repoUrl = repoUrl;
        commitList = new ArrayList<>();
        commitNumber = 0;
        totalCommitMessageSize = 0;
        mediumCommitMessageSize = 0;
    }

    public float getMediumCommitMessageSize() {
        return this.mediumCommitMessageSize;
    }

    public void setMediumCommitMessageSize() {
        this.mediumCommitMessageSize = totalCommitMessageSize / commitNumber;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public int getCommitNumber() {
        return commitNumber;
    }

    public void setCommitNumber(int commitNumber) {
        this.commitNumber = commitNumber;
    }

    public void addCommit(Commit commit) {
        commitList.add(commit);
        totalCommitMessageSize += commit.getMessage().length();
        commitNumber++;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "repoUrl='" + repoUrl + '\'' +
                ", commitNumber=" + commitNumber +
                ", totalCommitMessageSize=" + totalCommitMessageSize +
                ", mediumCommitMessageSize=" + mediumCommitMessageSize +
                '}';
    }
}
