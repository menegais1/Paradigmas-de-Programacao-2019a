package Model;

public class Commit {


    private String message;
    private Author author;

    public Commit(String message, Author author) {
        this.message = message;
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
